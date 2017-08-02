package categories

trait Ring[T] extends Monoid[T] with Group[T] {

  def mul(l: T, r: T): T
  def div(l: T, r: T): T

}
