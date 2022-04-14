package com.narcissus.marketplace.api.dataset

import com.narcissus.marketplace.api.dataset.model.ProductImage
import com.narcissus.marketplace.api.dataset.model.RawProduct
import com.narcissus.marketplace.api.model.Product
import com.narcissus.marketplace.api.model.Review
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.util.*
import kotlin.math.min
import kotlin.random.Random
import kotlin.random.nextInt

class DatasetGenerator {
    private val faker = Faker()
    private val parser = Parser()

    suspend fun generate(): List<Product> {
        val allImages: List<ProductImage> = withContext(Dispatchers.IO) {
            parser.parseImages()
        }

        return withContext(Dispatchers.Default) {
            allImages
                .map(::rawProductFromImage)
                .flatMap(::vary)
                .inflateSimilarProducts()
        }
    }

    private fun rawProductFromImage(image: ProductImage): RawProduct {
        val name = image.name.fromSnakeToTitleCase()
        val departmentId = image.depName.replace("_", "")
        val departmentName = image.depName.fromSnakeToTitleCase()

        return RawProduct(
            productImg150 = "https://api-narcissus-marketplace.herokuapp.com/pic/${image.depName}/${image.img150}",
            productImg300 = "https://api-narcissus-marketplace.herokuapp.com/pic/${image.depName}/${image.img300}",
            productImg600 = "https://api-narcissus-marketplace.herokuapp.com/pic/${image.depName}/${image.img600}",
            name = name,
            type = name,
            departmentId = departmentId,
            departmentName = departmentName,
        )
    }

    private fun String.fromSnakeToTitleCase(): String {
        return split("_")
            .map { it.replaceFirstChar(Char::uppercase) }
            .joinToString(" ")
    }

    private fun vary(raw: RawProduct): List<Product> {
        val commerceFaker = faker.commerce
        fun randomAdjective() = commerceFaker.productName().split(" ").first()
        return generateSequence {
            val id = UUID.randomUUID().toString()
            val name = "${randomAdjective()} ${raw.name}"
            val price = numberBetween(15, 2000)
            val inStock = numberBetween(10, 1000)
            val color = randomColor()
            val material = randomMaterial()
            val reviews = Array(5) { randomReview(id, name) }
            val rating = reviews.map { it.rating }.average().toInt()
            val sales = numberBetween(0, 2000)
            val description = "This is a description of ${name}. ${randomQuote()}"

            Product(
                id = id,
                name = name,
                productImg150 = raw.productImg150,
                productImg300 = raw.productImg300,
                productImg600 = raw.productImg600,
                departmentId = raw.departmentId,
                departmentName = raw.departmentName,
                type = raw.name,
                price = price,
                saleInfo = null,
                inStock = inStock,
                color = color,
                material = material,
                rating = rating,
                sales = sales,
                description = description,
                reviews = reviews.toList(),
                similarProductIds = emptyList(),
            )
        }
            .distinctBy { it.name }
            .take(4)
            .toList()
    }

    private fun randomColor() = faker.color.name().replaceFirstChar(Char::uppercase)

    private fun randomMaterial() = faker.commerce.productName().split(" ")[1]

    private fun randomReview(productId: String, productName: String) =
        Review(
            UUID.randomUUID().toString(),
            productId,
            randomName(),
            "This is my review of $productName. ${randomQuote()}",
            randomRating(),
            randomPhotoUrl()
        )

    private fun randomName() = names.random()()

    private fun randomQuote() = quotes.random()()

    private fun randomRating() = min(5, numberBetween(1, 7))

    private fun randomPhotoUrl() = if (Random.Default.nextBoolean()) {
        "https://randomuser.me/api/portraits/med/men/${numberBetween(1, 99)}.jpg"
    } else {
        "https://randomuser.me/api/portraits/thumb/women/${numberBetween(1, 99)}.jpg"
    }

    private fun numberBetween(i: Int, i1: Int): Int {
        return Random.Default.nextInt(i..i1)
    }

    private fun List<Product>.inflateSimilarProducts(): List<Product> {
        val groupedByDepartment = groupBy { it.departmentId }
        val result = mutableListOf<Product>()

        groupedByDepartment.values.forEach { departmentItems ->
            departmentItems.forEach { product ->
                val otherItemsInTheDepartment = departmentItems - product
                val similarProducts = otherItemsInTheDepartment.asSequence()
                    .filterNot { it.type == product.type }
                    .distinctBy { it.type }
                    .shuffled()
                    .take(2)
                    .toList()
                    .map {
                        it.id
                    }

                result += product.copy(similarProductIds = similarProducts)
            }
        }

        return result
    }

    private val quotes by lazy {
        with(faker) {
            setOf(
                backToTheFuture::quotes,
                bigBangTheory::quotes,
                buffy::quotes,
                control::quote,
                departed::quotes,
                drWho::quotes,
                dumbAndDumber::quotes,
                fallout::quotes,
                freshPriceOfBelAir::quotes,
                futurama::quotes,
                familyGuy::quote,
                gameOfThrones::quotes,
                ghostBusters::quotes,
                greekPhilosophers::quotes,
                harryPotter::quotes,
                heyArnold::quotes,
                hitchhikersGuideToTheGalaxy::quotes,
                hobbit::quote,
                howIMetYourMother::quote,
                leagueOfLegends::quote,
                lebowski::quotes,
                lordOfTheRings::quotes,
                michaelScott::quotes,
                myst::quotes,
                newGirl::quotes,
                onePiece::quotes,
                overwatch::quotes,
                rickAndMorty::quotes,
                siliconValley::quotes,
                simpsons::quotes,
                southPark::quotes,
                stargate::quotes,
                starWars::quote,
                twinPeaks::quotes,
                witcher::quotes,
                worldOfWarcraft::quotes,
            )
        }
    }

    private val names by lazy {
        with(faker) {
            setOf(
                backToTheFuture::characters,
                bigBangTheory::characters,
                buffy::characters,
                control::character,
                departed::characters,
                drWho::character,
                dumbAndDumber::characters,
                fallout::characters,
                freshPriceOfBelAir::characters,
                futurama::characters,
                familyGuy::character,
                gameOfThrones::characters,
                ghostBusters::characters,
                greekPhilosophers::names,
                harryPotter::characters,
                heyArnold::characters,
                hitchhikersGuideToTheGalaxy::characters,
                hobbit::character,
                howIMetYourMother::character,
                lebowski::characters,
                lordOfTheRings::characters,
                myst::characters,
                newGirl::characters,
                onePiece::characters,
                rickAndMorty::characters,
                siliconValley::characters,
                simpsons::characters,
                southPark::characters,
                stargate::characters,
                starWars::characters,
                twinPeaks::characters,
                witcher::characters,
            )
        }
    }
}

fun main() = runBlocking {
    val list = DatasetGenerator().generate()
    val json = File("src/main/resources/products.json")
    Json.encodeToStream(list, json.outputStream())
}