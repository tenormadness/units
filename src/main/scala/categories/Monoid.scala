package categories

/**
  * Created by luca on 12/8/16.
  */
trait Monoid[T] {

  def zero: T
  def append(l:T, r:T): T

}
