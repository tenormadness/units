package randomExperiment


import units.UnitsDefinitions._
/**
  * Created by lucatosatto on 8/25/16.
  */

case class UnitWrap[U, T](in: T) extends AnyVal {

  def apply[TT](f: T => TT): UnitWrap[U, TT] = UnitWrap[U, TT](f(in))

}


object draft extends App {

  val a = UnitWrap[Meter, Double](1.0)

  def plus2(in: Double) = in + 2

  a(plus2)

  a(2 + _)

}
