package main.scala.units


object UnitsDefinitions {

  sealed trait ErrorUnitNotFound

  case object Meter
  case object Second
  case object MeterPerSecond

  type Meter = Meter.type
  type Second = Second.type
  type MeterPerSecond = MeterPerSecond.type

}
