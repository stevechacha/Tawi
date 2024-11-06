package com.freshtawi.tawi.domain.model

import java.time.LocalDate

data class Farmer(
    val farmerId: Int,               // Unique identifier for the mango farmer
    val farmerName: String,          // Name of the mango farmer
    val phoneNumber: String,         // Contact number of the farmer
    val farms: List<Farm>,           // List of farms owned by the farmer
    val registrationDate:LocalDate  // Date when the farmer registered as a mango farmer
)

fun generateRandomFarmData(): List<Farm> {
    val random = java.util.Random()

    return List(10) {
        // Generate random farm data
        val farmId = random.nextInt(1000)
        val farmName = "Farm${random.nextInt(100)}"
        val farmLocation = "Location${random.nextInt(100)}"
        val farmSizeAcres = random.nextDouble() * 100
        val farmerNameFarm = "Farmer${random.nextInt(100)}"
        val phoneNumberFarm = "123-456-${random.nextInt(10000)}"

        // Generate random product data
        val product = List(3) {
            ProductCrop(
                "Crop${random.nextInt(10)}",
                "https://www.richfarmkenya.com/2023/01/mango-farming-in-kenya-how-to-grow-best.html"
            )
        }
        // Create instance
        Farm(
            farmId,
            farmName,
            farmLocation,
            product,
            farmSizeAcres,
            farmerNameFarm,
            phoneNumberFarm,
            "https://www.richfarmkenya.com/2023/01/mango-farming-in-kenya-how-to-grow-best.html"
        )
    }
}







