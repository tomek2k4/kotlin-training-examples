package obiektowe

interface Movable {
    var density: Double
    fun move()
}

class Box(val xyzWallsLength: Double) : Movable {
    constructor(
        xWallLength: Double,
        yWallLength: Double,
        zWallLenth: Double
    ) : this(xWallLength + yWallLength + zWallLenth)

    override var density: Double = 1.0

    override fun move() {
        println("$density density, $xyzWallsLength sum of walls")
    }
}
