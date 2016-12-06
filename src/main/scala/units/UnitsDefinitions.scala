package units

/**
 * Created by lucatosatto on 8/25/16.
 */
object UnitsDefinitions {

//  sealed trait Meter
//  sealed trait Second
//  sealed trait MeterPerSecond

  sealed trait ErrorUnitNotFound

  case object meter
  case object second
  case object meterPerSecond

  type Meter = meter.type
  type Second = second.type
  type MeterPerSecond = meterPerSecond.type
// case object Meter extends Meter
//  case object Second extends Second
//  case object MeterPerSecond extends MeterPerSecond

}
