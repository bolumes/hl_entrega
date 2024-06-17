import com.example.hl_entrega.Models.User
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class UserApiService {

    fun createUser(user: User) {
        val url = URL("http://localhost:3000/api/usuarios") // URL da rota para criar usuário
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8")
        conn.doOutput = true

        try {
            val jsonInputString = """
                {
                    "fullname": "${user.fullname}",
                    "phonenumber": "${user.phonenumber}",
                    "email": "${user.email}",
                    "password": "${user.password}",
                    "address": "${user.address}",
                    "type": "${user.type}"
                }
            """.trimIndent()

            val outputStream = conn.outputStream
            val writer = OutputStreamWriter(outputStream)
            writer.write(jsonInputString)
            writer.flush()
            writer.close()

            // Verificar a resposta da API
            if (conn.responseCode == HttpURLConnection.HTTP_CREATED) {
                // Leitura da resposta, se necessário
                println("Usuário criado com sucesso no servidor.")
            } else {
                // Tratar erro na comunicação com a API
                println("Erro ao criar usuário no servidor. Código de resposta: ${conn.responseCode}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            conn.disconnect()
        }
    }


}
