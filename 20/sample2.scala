class Food
abstract class Animal {
  type SuitableFood <: Food
  def eat(food: SuitableFood)
}

class Grass extends Food

class Cow extends Animal {
  type SuitableFood = Grass
  override def eat(food: Grass) {}
}

class DogFood extends Food
class Dog extends Animal {
  type SuitableFood = DogFood
  override def eat(food: DogFood) {}
}


implicit class Sample {
  def int : Int = 10
  
}
