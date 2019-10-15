package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_actualizar_almacen.*

class Actualizar_almacen : AppCompatActivity() {

    private var edit_buscar_actualizar : EditText?=null
    private var edit_actualizar_producto : EditText?=null
    private var edit_actualiza_cantidad : EditText?=null
    private var edit_actualizar_precio : EditText?=null
    private var boton_actualizar : Button?=null
    private var boton_buscar : Button?=null
    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_almacen)
        setSupportActionBar(cabezera_Actualizar_Almacen)
        title = "Actualizar_Almacen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edit_buscar_actualizar = findViewById(R.id.edit_Buscar_Actualizar)
        edit_actualizar_producto = findViewById(R.id.edit_Producto_Actualizar)
        edit_actualiza_cantidad = findViewById(R.id.edit_Cantidad_Actualizar)
        edit_actualizar_precio = findViewById(R.id.edit_Precio_Actualizar)
        boton_actualizar = findViewById(R.id.boton_Actualizar_Almacen)
        boton_buscar = findViewById(R.id.boton_Buscar_Actualizar)

        boton_buscar?.setOnClickListener {
            buscar(edit_buscar_actualizar?.text.toString())
            Toast.makeText(this,"Jalate Perro",Toast.LENGTH_LONG).show()
        }

        boton_actualizar?.setOnClickListener {
            aplicarActualizar()
        }
    }

    private fun buscar(producto : String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "SELECT * FROM ALMACEN WHERE PRODUCTO= '" + producto +"'"

            var respuesta = transaccion.rawQuery(SQL, null)
            if (respuesta.moveToFirst()==true) {
                var Traeproducto = respuesta.getString(1)
                var Traecantidad = respuesta.getString(2)
                var Traeprecio = respuesta.getString(3)

                edit_actualizar_producto?.setText(Traeproducto)
                edit_actualiza_cantidad?.setText(Traecantidad)
                edit_actualizar_precio?.setText(Traeprecio)
                transaccion.close()
            }
        } catch (err: SQLiteException) {
            mensaje("ERROR", "AL PARECER NO EXISTE LA DESCRIPCION")
        }
    }

    private fun aplicarActualizar() {
        try {
            var transaccion = basedatos.writableDatabase
            var SQL = "UPDATE ALMACEN SET PRODUCTO='CAMPOPRODUCTO', CANTIDAD ='CAMPOCANTIDAD', PRECIO ='CAMPOPRECIO' WHERE ID_ALMACEN=ID_ALMACEN "

            if (validarCampos() == false) {
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")
                return
            }
            SQL = SQL.replace("CAMPOPRODUCTO", edit_actualizar_producto?.text.toString())
            SQL = SQL.replace("CAMPOCANTIDAD", edit_actualiza_cantidad?.text.toString())
            SQL = SQL.replace("CAMPOPRECIO", edit_actualizar_precio?.text.toString())

            transaccion.execSQL(SQL)
            transaccion.close()//CIERRA LA TRANSACCION
            mensaje("ACTUALIZADO","SE ACTUALIZO CORRECTAMENTE")
            limpiar()
        } catch (err: SQLiteException) {
            mensaje("Error", "NO SE PUDO INSERTAR, TAL VEZ ID YA EXISTE")
        }
    }

    fun validarCampos(): Boolean {
        if (edit_actualizar_producto?.text.toString().isEmpty() || edit_actualiza_cantidad?.text.toString().isEmpty() || edit_actualizar_precio?.text.toString().isEmpty()) {
            return false
        } else {
            return true
        }
    }

    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("Ok") { dialog, whitch -> }
            .show()
    }

    fun limpiar() {
        edit_buscar_actualizar?.setText("")
        edit_actualizar_producto?.setText("")
        edit_actualiza_cantidad?.setText("")
        edit_actualizar_precio?.setText("")
    }

    //Regresar
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else
            super.onOptionsItemSelected(item)
    }
}
