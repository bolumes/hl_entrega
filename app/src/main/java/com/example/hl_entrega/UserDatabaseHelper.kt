import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "app.db"
        private const val DATABASE_VERSION = 1

        // Tabela User
        const val TABLE_USER = "User"
        const val USER_ID = "id"
        const val USER_NAME = "nome"
        const val USER_PHONE = "telefone"
        const val USER_EMAIL = "email"
        const val USER_PASSWORD = "senha"

        // Tabela Restaurant
        const val TABLE_RESTAURANT = "Restaurant"
        const val RESTAURANT_ID = "id"
        const val RESTAURANT_NAME = "nome"
        const val RESTAURANT_PHONE = "telefone"
        const val RESTAURANT_EMAIL = "email"
        const val RESTAURANT_ADDRESS = "endereco"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUserTable = ("CREATE TABLE $TABLE_USER ("
                + "$USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$USER_NAME TEXT, "
                + "$USER_PHONE TEXT, "
                + "$USER_EMAIL TEXT, "
                + "$USER_PASSWORD TEXT)")

        val createRestaurantTable = ("CREATE TABLE $TABLE_RESTAURANT ("
                + "$RESTAURANT_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$RESTAURANT_NAME TEXT, "
                + "$RESTAURANT_PHONE TEXT, "
                + "$RESTAURANT_EMAIL TEXT, "
                + "$RESTAURANT_ADDRESS TEXT)")

        db.execSQL(createUserTable)
        db.execSQL(createRestaurantTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RESTAURANT")
        onCreate(db)
    }



    // Função para inserir usuário
    fun insertUser(nome: String, telefone: String, email: String, senha: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(USER_NAME, nome)
            put(USER_PHONE, telefone)
            put(USER_EMAIL, email)
            put(USER_PASSWORD, senha)
        }
        return db.insert(TABLE_USER, null, contentValues)
    }

    // Função para inserir restaurante
    fun insertRestaurant(nome: String, telefone: String, email: String, endereco: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(RESTAURANT_NAME, nome)
            put(RESTAURANT_PHONE, telefone)
            put(RESTAURANT_EMAIL, email)
            put(RESTAURANT_ADDRESS, endereco)
        }
        return db.insert(TABLE_RESTAURANT, null, contentValues)
    }
}
