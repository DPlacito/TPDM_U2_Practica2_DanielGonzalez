package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez

import android.content.DialogInterface
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_eliminar_almacen.*

class Eliminar_almacen : AppCompatActivity() {

    private var edit_buscar : EditText?=null
    private var text_id : TextView?=null
    private var text_producto : TextView?=null
    private var text_cantidad : TextView?=null
    private var text_precio :TextView?=null
    private var boton_eliminar : Button?=null
    private var boton_buscar : Button?=null

    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_almacen)
        setSupportActionBar(cabezera_Eliminar_Almacen)
        title = "Eliminar_Almacen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        boton_buscar = findViewById(R.id.boton_Buscar_Eliminar)
        boton_eliminar = findViewById(R.id.boton_Eliminar_Almacen)
        edit_buscar = findViewById(R.id.edit_Buscar_Eliminar)
        text_id = findViewById(R.id.text_ID)
        text_producto = findViewById(R.id.text_Producto)
        text_cantidad= findViewById(R.id.text_Cantidad)
        text_precio = findViewById(R.id.text_Precio)


        boton_buscar?.setOnClickListener {
            buscar(edit_buscar?.text.toString())
        }

        boton_eliminar?.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("CUIDADO!!")
            dialog.setMessage("Estas Seguro De Que Quieres Eliminar Este Producto ?")
            dialog.setPositiveButton("Si Eliminar") { _: DialogInterface, _: Int ->
                elimina(text_producto?.text.toString())
            }
            dialog.setNeutralButton("Cancelar") { _: DialogInterface, _: Int ->

            }
            dialog.show()
        }

    }

    private fun buscar(producto : String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "SELECT * FROM ALMACEN WHERE PRODUCTO= '" + producto +"'"

            var respuesta = transaccion.rawQuery(SQL, null)
            if (respuesta.moveToFirst()==true) {
                var TraeID = respuesta.getString(0)
                var Traeproducto = respuesta.getString(1)
                var Traecantidad = respuesta.getString(2)
                var Traeprecio = respuesta.getString(3)

                text_id?.setText(TraeID)
                text_producto?.setText(Traeproducto)
                text_cantidad?.setText(Traecantidad)
                text_precio?.setText(Traeprecio)
                transaccion.close()
            }
        } catch (err: SQLiteException) {
            mensaje("ERROR", "AL PARECER NO EXISTE LA DESCRIPCION")
        }
    }

    fun elimina(producto: String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "DELETE FROM ALMACEN WHERE PRODUCTO= '"+producto+"'"
            transaccion.execSQL(SQL)
            transaccion.close()
            limpiar()
            Toast.makeText(this, "Producto Eliminada", Toast.LENGTH_LONG).show()
        } catch (err: SQLiteException) {
            mensaje("ERROR", "NO SE LOGRO ELIMINAR")
        }

    }

    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("Ok") { dialog, whitch -> }
            .show()
    }

    fun limpiar() {
        edit_buscar?.setText("")
        text_cantidad?.setText("")
        text_ID?.setText("")
        text_producto?.setText("")
        text_precio?.setText("")
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
