import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlFactory
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

fun provideRetrofit(): Retrofit {

    val xmlMapper = XmlMapper(XmlFactory()).apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    val okHttpClient = OkHttpClient.Builder()
        .build()

    return Retrofit.Builder()
        .baseUrl("https://google.com")
        .client(okHttpClient)
        .addConverterFactory(JacksonConverterFactory.create(xmlMapper))
        .build()
}
