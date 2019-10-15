package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?, // cual es el activity
    name: String?, //
    factory: SQLiteDatabase.CursorFactory?, // sub clase  y me genera una variable factory
    version: Int //segun hagas cambios en la escructura vas a incrementar la version
//Esta es un clase padre, personal
) : SQLiteOpenHelper(context, name, factory, version) {



    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE ALMACEN (ID_ALMACEN INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCTO VARCHAR(400), CANTIDAD INTEGER, PRECIO FLOAT)")
        p0?.execSQL("CREATE TABLE CLIENTE (ID_CLIENTE INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR(400), DOMICILIO VARCHAR(400),ID_EMPRESA INTEGER, FOREIGN KEY (ID_EMPRESA) REFERENCES EMPRESA(ID_EMPRESA))")
        p0?.execSQL("CREATE TABLE DETALLECOMPRA (ID_DETALLE INTEGER PRIMARY KEY AUTOINCREMENT, CANTIDAD INTEGER, PRECIO FLOAT,ID_ALMACEN INTEGER, FOREIGN KEY (ID_ALMACEN) REFERENCES ALMACEN (ID_ALMACEN))")
        p0?.execSQL("CREATE TABLE COMPRA (ID_COMPRA INTEGER PRIMARY KEY AUTOINCREMENT, FECHA DATE, TOTAL FLOAT,ID_CLIENTE INTEGER, FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE))")
        p0?.execSQL("CREATE TABLE EMPRESA (ID_EMPRESA INTEGER PRIMARY KEY AUTOINCREMENT, DESCRIPCION VARCHAR(30), DOMICILIO VARCHAR(50))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}

//ID_COMPRA INTEGER, FOREIGN KEY (ID_COMPRA) REFERENCES COMPRA(ID_COMPRA)