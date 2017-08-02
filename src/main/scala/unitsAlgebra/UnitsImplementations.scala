package unitsAlgebra

object UnitsImplementations {

  trait Meter
  trait MeterSq
  trait Second
  trait MeterPerSecond
  trait MeterPerSecondSq
  trait OneOvrMeter

  implicit val UnitRule1 = new (MeterPerSecond MultiplyBy Second) .yields [Meter]
  implicit val UnitRule2 = new (MeterPerSecond DivideBy Second) .yields [MeterPerSecondSq]
  implicit val UnitRule3 = new (Meter MultiplyBy Meter) .yields [MeterSq]
  implicit val UnitRule4 = new (Meter IsInverseOf OneOvrMeter)

}

