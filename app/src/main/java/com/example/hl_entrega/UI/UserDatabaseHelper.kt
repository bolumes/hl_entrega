package com.example.hl_entrega

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.hl_entrega.Models.Admin
import com.example.hl_entrega.Models.Command
import com.example.hl_entrega.Models.Menu
import com.example.hl_entrega.Models.Restaurant
import com.example.hl_entrega.Models.User

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "entrega.db"
        private const val DATABASE_VERSION = 1

        // TABELA DE USUARIOS
        private const val TABLE_USERS = "allUsers"
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_USER_FULLNAME = "fullname"
        private const val COLUMN_USER_PHONENUMBER = "phonenumber"
        private const val COLUMN_USER_EMAIL = "email"
        private const val COLUMN_USER_PASSWORD = "password"
        private const val COLUMN_USER_ADDRESS = "address"

        // TABELA DE RESTAURANTES
        private const val TABLE_RESTAURANTS = "allRestaurants"
        private const val COLUMN_RESTAURANT_ID = "id"
        private const val COLUMN_RESTAURANT_FULLNAME = "fullname"
        private const val COLUMN_RESTAURANT_PHONENUMBER = "phonenumber"
        private const val COLUMN_RESTAURANT_EMAIL = "email"
        private const val COLUMN_RESTAURANT_ADDRESS = "address"

        // TABELA DE ADMIN
        private const val TABLE_ADMINS = "allAdmin"
        private const val COLUMN_ADMIN_ID = "id"
        private const val COLUMN_ADMIN_FULLNAME = "fullname"
        private const val COLUMN_ADMIN_PHONENUMBER = "phonenumber"
        private const val COLUMN_ADMIN_EMAIL = "email"
        private const val COLUMN_ADMIN_PASSWORD = "password"
        private const val COLUMN_ADMIN_ADDRESS = "address"

        // TABELA DE MENU
        private const val TABLE_MENUS = "allMenu"
        private const val COLUMN_MENU_ID = "idM"
        private const val COLUMN_MENU_DATA = "dataM"
        private const val COLUMN_MENU_DESCRIPTION = "descriptionM"

        // TABELA DE MENU
        private const val TABLE_COMMANDS = "allCommand"
        private const val COLUMN_COMMANDS_ID = "idC"
        private const val COLUMN_COMMANDS_DATA = "dataC"
        private const val COLUMN_COMMANDS_DESCRIPTION = "descriptionC"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        // Criar Tabela de Command
        val createCommandTableQuery = """
            CREATE TABLE $TABLE_COMMANDS (
                $COLUMN_COMMANDS_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COLUMN_COMMANDS_DATA TEXT, 
                $COLUMN_COMMANDS_DESCRIPTION TEXT
            )
        """.trimIndent()
        db?.execSQL(createCommandTableQuery)



        // Criar Tabela de Menu
        val createMenuTableQuery = """
            CREATE TABLE $TABLE_MENUS (
                $COLUMN_MENU_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COLUMN_MENU_DATA TEXT, 
                $COLUMN_MENU_DESCRIPTION TEXT
            )
        """.trimIndent()
        db?.execSQL(createMenuTableQuery)


        // Criar Tabela de Admin
        val createAdminTableQuery = """
            CREATE TABLE $TABLE_ADMINS (
                $COLUMN_ADMIN_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COLUMN_ADMIN_FULLNAME TEXT, 
                $COLUMN_ADMIN_PHONENUMBER TEXT, 
                $COLUMN_ADMIN_EMAIL TEXT, 
                $COLUMN_ADMIN_PASSWORD TEXT,
                $COLUMN_ADMIN_ADDRESS TEXT
            )
        """.trimIndent()
        db?.execSQL(createAdminTableQuery)

        // Criar Tabela de Usuários
        val createUserTableQuery = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COLUMN_USER_FULLNAME TEXT, 
                $COLUMN_USER_PHONENUMBER TEXT, 
                $COLUMN_USER_EMAIL TEXT, 
                $COLUMN_USER_PASSWORD TEXT,
                $COLUMN_USER_ADDRESS TEXT
            )
        """.trimIndent()
        db?.execSQL(createUserTableQuery)

        // Criar Tabela de Restaurantes
        val createRestaurantTableQuery = """
            CREATE TABLE $TABLE_RESTAURANTS (
                $COLUMN_RESTAURANT_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COLUMN_RESTAURANT_FULLNAME TEXT, 
                $COLUMN_RESTAURANT_PHONENUMBER TEXT, 
                $COLUMN_RESTAURANT_EMAIL TEXT, 
                $COLUMN_RESTAURANT_ADDRESS TEXT
            )
        """.trimIndent()
        db?.execSQL(createRestaurantTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ADMINS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_RESTAURANTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MENUS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_COMMANDS")
        onCreate(db)
    }


    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


    // OPERAÇÕES DE ADMIN

    // Método para fazer o registo do admin
    fun insertAdmin(admin: Admin) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ADMIN_FULLNAME, admin.fullname)
            put(COLUMN_ADMIN_PHONENUMBER, admin.phonenumber)
            put(COLUMN_ADMIN_EMAIL, admin.email)
            put(COLUMN_ADMIN_PASSWORD, admin.password)
            put(COLUMN_ADMIN_ADDRESS, admin.address)
        }
        try {
            val newRowId = db.insertOrThrow(TABLE_ADMINS, null, values)
            if (newRowId == -1L) {
                Log.e("DB_ERROR", "Erro ao inserir Admin")
            } else {
                Log.d("DB_SUCCESS", "Admin inserido com sucesso, ID: $newRowId")
            }
        } catch (e: Exception) {
            Log.e("DB_ERROR", "Erro ao inserir Admin: ${e.message}")
        } finally {
            db.close()
        }
    }

    // Método para saber da existencia do admin
    fun isAdminExist(email: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_ADMIN_EMAIL = ? AND $COLUMN_ADMIN_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        return try {
            val cursor = db.query(TABLE_ADMINS, null, selection, selectionArgs, null, null, null)
            val adminExists = cursor.count > 0
            cursor.close()
            adminExists
        } catch (e: Exception) {
            Log.e("DB_ERROR", "Erro ao buscar administrador: ${e.message}")
            false
        }
    }

    // Método para obter a lista dos admins
    fun getAllAdmins(): List<Admin> {
        val adminsList = mutableListOf<Admin>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_ADMINS"
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ADMIN_ID))
                val fullname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADMIN_FULLNAME))
                val phonenumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADMIN_PHONENUMBER))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADMIN_EMAIL))
                val password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADMIN_PASSWORD))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADMIN_ADDRESS))

                val admin = Admin(id, fullname, phonenumber, email, password, address)
                adminsList.add(admin)
            }
        }
        db.close()
        return adminsList
    }

    // Método para obter o ID do admin
    fun getAdminId(email: String, password: String): Int? {
        val db = this.readableDatabase
        val query = "SELECT id FROM admins WHERE email = ? AND password = ?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        return if (cursor.moveToFirst()) {
            cursor.getInt(cursor.getColumnIndexOrThrow("id"))
        } else {
            null
        }.also {
            cursor.close()
        }
    }

    // Funçao para update do admin
    fun updateAdmin(admin: Admin): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ADMIN_FULLNAME, admin.fullname)
            put(COLUMN_ADMIN_PHONENUMBER, admin.phonenumber)
            put(COLUMN_ADMIN_EMAIL, admin.email)
            put(COLUMN_ADMIN_PASSWORD, admin.password)
            put(COLUMN_ADMIN_ADDRESS, admin.address)
        }

        val selection = "$COLUMN_ADMIN_ID = ?"
        val selectionArgs = arrayOf(admin.id.toString())

        return try {
            val count = db.update(TABLE_ADMINS, values, selection, selectionArgs)
            if (count == 1) {
                Log.d("DB_SUCCESS", "Admin atualizado com sucesso, ID: ${admin.id}")
            } else {
                Log.e("DB_ERROR", "Erro ao atualizar admin com ID: ${admin.id}")
            }
            count == 1
        } catch (e: Exception) {
            Log.e("DB_ERROR", "Erro ao atualizar admin: ${e.message}")
            false
        } finally {
            db.close()
        }
    }


    // Function to get admin ID by email and password
    fun getAdminIdByEmailAndPassword(email: String, password: String): Int? {
        val db = readableDatabase
        val selection = "$COLUMN_ADMIN_EMAIL = ? AND $COLUMN_ADMIN_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(TABLE_ADMINS, arrayOf(COLUMN_ADMIN_ID), selection, selectionArgs, null, null, null)

        return if (cursor.moveToFirst()) {
            val adminId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ADMIN_ID))
            cursor.close()
            adminId
        } else {
            cursor.close()
            null
        }
    }


    fun getAdminIdByEmail(email: String): Int {
        val db = this.readableDatabase
        val query = "SELECT id FROM Admins WHERE email = ?"
        val cursor = db.rawQuery(query, arrayOf(email))
        var adminId = -1
        if (cursor.moveToFirst()) {
            adminId = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return adminId
    }


    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    // OPERAÇÕES DE USUARIOS

    // Método para fazer resgisto do usuário
    fun insertUser(user: User) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_FULLNAME, user.fullname)
            put(COLUMN_USER_PHONENUMBER, user.phonenumber)
            put(COLUMN_USER_EMAIL, user.email)
            put(COLUMN_USER_PASSWORD, user.password)
            put(COLUMN_USER_ADDRESS, user.address)
        }
        try {
            val newRowId = db.insertOrThrow(TABLE_USERS, null, values)
            if (newRowId == -1L) {
                Log.e("DB_ERROR", "Erro ao inserir usuário")
            } else {
                Log.d("DB_SUCCESS", "Usuário inserido com sucesso, ID: $newRowId")
            }
        } catch (e: Exception) {
            Log.e("DB_ERROR", "Erro ao inserir usuário: ${e.message}")
        } finally {
            db.close()
        }
    }

    // Método para obter a lista dos usuários
    fun getAllUsers(): List<User> {
        val usersList = mutableListOf<User>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_USERS"
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID))
                val fullname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_FULLNAME))
                val phonenumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PHONENUMBER))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_EMAIL))
                val password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PASSWORD))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_ADDRESS))

                val user = User(id, fullname, phonenumber, email, password, address)
                usersList.add(user)
            }
        }
        db.close()
        return usersList
    }

    // Método para saber da existencia do usuário
    fun isUserExist(email: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    // Método para obter o ID do usuário
    fun getUserId(email: String, password: String): Int? {
        val db = this.readableDatabase
        val query = "SELECT id FROM users WHERE email = ? AND password = ?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        return if (cursor.moveToFirst()) {
            cursor.getInt(cursor.getColumnIndexOrThrow("id"))
        } else {
            null
        }.also {
            cursor.close()
        }
    }

    // Funçao para obter o update do user
    fun updateUser(user: User): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_FULLNAME, user.fullname)
            put(COLUMN_USER_PHONENUMBER, user.phonenumber)
            put(COLUMN_USER_EMAIL, user.email)
            put(COLUMN_USER_PASSWORD, user.password)
            put(COLUMN_USER_ADDRESS, user.address)
        }

        val selection = "$COLUMN_USER_ID = ?"
        val selectionArgs = arrayOf(user.id.toString())

        return try {
            val count = db.update(TABLE_USERS, values, selection, selectionArgs)
            if (count == 1) {
                Log.d("DB_SUCCESS", "Usuário atualizado com sucesso, ID: ${user.id}")
            } else {
                Log.e("DB_ERROR", "Erro ao atualizar usuário com ID: ${user.id}")
            }
            count == 1
        } catch (e: Exception) {
            Log.e("DB_ERROR", "Erro ao atualizar usuário: ${e.message}")
            false
        } finally {
            db.close()
        }
    }


    // Function to get user ID by email and password
    fun getUserIdByEmailAndPassword(email: String, password: String): Int? {
        val db = readableDatabase
        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(TABLE_USERS, arrayOf(COLUMN_USER_ID), selection, selectionArgs, null, null, null)

        return if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID))
            cursor.close()
            userId
        } else {
            cursor.close()
            null
        }
    }

    fun getUserIdByEmail(email: String): Int {
        val db = this.readableDatabase
        val query = "SELECT id FROM Users WHERE email = ?"
        val cursor = db.rawQuery(query, arrayOf(email))
        var userId = -1
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return userId
    }

    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


    // Operações de Restaurantes
    fun insertRestaurant(restaurant: Restaurant) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_RESTAURANT_FULLNAME, restaurant.fullname)
            put(COLUMN_RESTAURANT_PHONENUMBER, restaurant.phonenumber)
            put(COLUMN_RESTAURANT_EMAIL, restaurant.email)
            put(COLUMN_RESTAURANT_ADDRESS, restaurant.address)
        }
        try {
            val newRowId = db.insertOrThrow(TABLE_RESTAURANTS, null, values)
            if (newRowId == -1L) {
                Log.e("DB_ERROR", "Erro ao inserir restaurante")
            } else {
                Log.d("DB_SUCCESS", "Restaurante inserido com sucesso, ID: $newRowId")
            }
        } catch (e: Exception) {
            Log.e("DB_ERROR", "Erro ao inserir restaurante: ${e.message}")
        } finally {
            db.close()
        }
    }

    fun getAllRestaurants(): List<Restaurant> {
        val restaurantsList = mutableListOf<Restaurant>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_RESTAURANTS"
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_ID))
                val fullname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_FULLNAME))
                val phonenumber = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_PHONENUMBER))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_EMAIL))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_ADDRESS))

                val restaurant = Restaurant(id, fullname, phonenumber, email, address)
                restaurantsList.add(restaurant)
            }
        }
        db.close()
        return restaurantsList
    }


    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


    // OPERAÇÕES DE MENU
    // Function to insert menu into the database
    fun insertMenu(menu: Menu) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MENU_DATA, menu.dataM)
            put(COLUMN_MENU_DESCRIPTION, menu.descriptionM)
        }
        try {
            val newRowId = db.insertOrThrow(TABLE_MENUS, null, values)
            if (newRowId == -1L) {
                Log.e("DB_ERROR", "Error inserting menu")
            } else {
                Log.d("DB_SUCCESS", "Menu successfully inserted, ID: $newRowId")
            }
        } catch (e: Exception) {
            Log.e("DB_ERROR", "Error inserting menu: ${e.message}")
        } finally {
            db.close()
        }
    }


    fun getAllMenus(): List<Menu> {
        val menusList = mutableListOf<Menu>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_MENUS"
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MENU_ID))
                val data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MENU_DATA))
                val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MENU_DESCRIPTION))

                val menu = Menu(id, data, description)
                menusList.add(menu)
            }
        }
        db.close()
        return menusList
    }


    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


    // OPERAÇÕES DE COMMAND
    fun insertCommand(command: Command) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_COMMANDS_DATA, command.dataC)
            put(COLUMN_COMMANDS_DESCRIPTION, command.descriptionC)
        }
        try {
            val newRowId = db.insertOrThrow(TABLE_COMMANDS, null, values)
            if (newRowId == -1L) {
                Log.e("DB_ERROR", "Erro ao inserir menu")
            } else {
                Log.d("DB_SUCCESS", "Menu inserido com sucesso, ID: $newRowId")
            }
        } catch (e: Exception) {
            Log.e("DB_ERROR", "Erro ao inserir menu: ${e.message}")
        } finally {
            db.close()
        }
    }



        // Função para obter todos os comandos

        fun getAllCommands(): List<Command> {
            val commandList = mutableListOf<Command>()
            val db = readableDatabase
            val query = "SELECT * FROM $TABLE_COMMANDS"
            val cursor = db.rawQuery(query, null)

            cursor.use {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COMMANDS_ID))
                    val data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMANDS_DATA))
                    val description = cursor.getString(cursor.getColumnIndexOrThrow(
                        COLUMN_COMMANDS_DESCRIPTION))

                    val command = Command(id, data, description)
                    commandList.add(command)
                }
            }
            db.close()
            return commandList
        }


        // Função para obter um comando pelo ID
        fun getCommandById(commandId: Int): Command? {
            val db = readableDatabase
            val selection = "$COLUMN_COMMANDS_ID = ?"
            val selectionArgs = arrayOf(commandId.toString())
            val cursor = db.query(TABLE_COMMANDS, null, selection, selectionArgs, null, null, null)

            return if (cursor.moveToFirst()) {
                val idC = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COMMANDS_ID))
                val dataC = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMANDS_DATA))
                val descriptionC = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMANDS_DESCRIPTION))
                cursor.close()
                Command(idC, dataC, descriptionC)
            } else {
                cursor.close()
                null
            }
        }

        // Função para atualizar um comando
        fun updateCommand(command: Command): Boolean {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_COMMANDS_DATA, command.dataC)
                put(COLUMN_COMMANDS_DESCRIPTION, command.descriptionC)
            }

            val selection = "$COLUMN_COMMANDS_ID = ?"
            val selectionArgs = arrayOf(command.idC.toString())

            return try {
                val count = db.update(TABLE_COMMANDS, values, selection, selectionArgs)
                if (count == 1) {
                    Log.d("DB_SUCCESS", "Comando atualizado com sucesso, ID: ${command.idC}")
                } else {
                    Log.e("DB_ERROR", "Erro ao atualizar comando com ID: ${command.idC}")
                }
                count == 1
            } catch (e: Exception) {
                Log.e("DB_ERROR", "Erro ao atualizar comando: ${e.message}")
                false
            } finally {
                db.close()
            }
        }

        // Função para deletar um comando pelo ID
        fun deleteCommand(commandId: Int): Boolean {
            val db = writableDatabase
            val selection = "$COLUMN_COMMANDS_ID = ?"
            val selectionArgs = arrayOf(commandId.toString())

            return try {
                val deletedRows = db.delete(TABLE_COMMANDS, selection, selectionArgs)
                if (deletedRows == 1) {
                    Log.d("DB_SUCCESS", "Comando deletado com sucesso, ID: $commandId")
                } else {
                    Log.e("DB_ERROR", "Erro ao deletar comando com ID: $commandId")
                }
                deletedRows == 1
            } catch (e: Exception) {
                Log.e("DB_ERROR", "Erro ao deletar comando: ${e.message}")
                false
            } finally {
                db.close()
            }
        }


    // Método para obter o nome do usuário com base no email e senha
    fun getUserName(email: String, password: String): String? {
        val db = this.readableDatabase
        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_USER_FULLNAME), // Procurando pela coluna "fullname"
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_FULLNAME))
        } else {
            null
        }.also {
            cursor.close()
            db.close()
        }
    }



}


