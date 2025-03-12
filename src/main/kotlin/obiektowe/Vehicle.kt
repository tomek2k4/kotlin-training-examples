package org.example.obiektowe

class Vehicle {

    var direction: String = "Parked"

    var speed: Int = 0
        private set

    private var oilLevel: Int = 20

    fun drive() {
        println("Vehicle is driving $direction")
        speed = 20
    }
}

class VehicleWrapper(private val vehicle: Vehicle) {

    var relatedUserId: Long = -1
        set(value) {
            field = value
        }
        get() = field

    fun doWithVehicle() {
        vehicle.direction = "Forward"
        println( vehicle.speed)
        vehicle.drive()
    }
}


