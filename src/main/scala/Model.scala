/*
 * Copyright (C) 2026 Romain Reuillon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import better.files.*

object Model:
  def virus(graph: File, seed: Int): Double =
    import scala.sys.process.*

    println(s"docker exec virus /usr/bin/virus ${graph.pathAsString} $seed")
    val lines =
      val id = java.util.UUID.randomUUID().toString
      val cp = Process(s"docker cp ${graph.pathAsString} virus:/tmp/$id")
      val run = Process(s"docker exec virus /usr/bin/virus /tmp/$id $seed") //lazyLines(ProcessLogger.apply(_ => ()))
      val rm = Process(s"docker exec virus rm /tmp/$id")

      (cp #&& run #&& rm).lazyLines(ProcessLogger.apply(_ => ()))

    lines.head.split(",").map(_.toDouble).max

  @main def run =
    println:
      virus(File("./netlogo/big.gml"), 52)