package mx.mariovaldez.ruutcodechallenge.ktx

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import mx.mariovaldez.ruutcodechallenge.BuildConfig
import mx.mariovaldez.ruutcodechallenge.util.Constants
import mx.mariovaldez.ruutcodechallenge.util.Constants.token
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID

fun getUUID() = UUID.randomUUID().toString()

fun getToken(email: String, expirationDate: Date): String {
    val key = Keys.hmacShaKeyFor(Constants.secret.toByteArray())
    val jws = Jwts.builder()
        .setExpiration(expirationDate)
        .setIssuer(Constants.issuer)
        .signWith(key, SignatureAlgorithm.HS256)
        .claim("email", email)
        .compact();
    return jws
}

fun getExpirationDate(): Date {
    val calendar = Calendar.getInstance()

    calendar.add(Calendar.HOUR_OF_DAY, 8)
    return calendar.time
}

fun createImageUrl(company: String): String =
    "https://storage.googleapis.com/iexcloud-hl37opg/api/logos/$company.png"

fun String.firstNameFormat(): String {
    val names = this.split(" ")
    return names[0].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun getFullNameFormat(name: String, lastName: String) = "${name.capitalizeName()} ${lastName.capitalizeName()}"

fun String.capitalizeName() =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
