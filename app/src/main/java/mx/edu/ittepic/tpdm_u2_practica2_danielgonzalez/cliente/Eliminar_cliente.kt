package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.cliente

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
import kotlinx.android.synthetic.main.activity_eliminar_cliente.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.BaseDatos
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Eliminar_cliente : AppCompatActivity() {

    private var edit_buscar : EditText?=null
    private var text_id : TextView?=null
    private var text_nombre : TextView?=null
    private var text_domicilio : TextView?=null
    private var boton_eliminar : Button?=null
    private var boton_buscar : Button?=null

    var basedatos = BaseDatos(this, "practica2", null, 2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_cliente)
        setSupportActionBar(cabezera_Eliminar_C)
        title = "Eliminar_Cliente"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        boton_buscar = findViewById(R.id.boton_Buscar_Eliminar_C)
        boton_eliminar = findViewById(R.id.boton_Eliminar_Almacen_C)
        edit_buscar = findViewById(R.id.edit_Buscar_Eliminar_C)
        text_id = findViewById(R.id.text_ID_C)
        text_nombre= findViewById(R.id.text_Nombre_C)
        text_domicilio = findViewById(R.id.text_Domicilio_C)

        boton_buscar?.setOnClickListener {
            buscar(edit_buscar?.text.toString())
        }

        boton_eliminar?.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("CUIDADO!!")
            dialog.setMessage("Estas Seguro De Que Quieres Eliminar Este Cliente ?")
            dialog.setPositiveButton("Si Eliminar") { _: DialogInterface, _: Int ->
                elimina(text_nombre?.text.toString())
            }
            dialog.setNeutralButton("Cancelar") { _: DialogInterface, _: Int ->

            }
            dialog.show()


            //
        }

    }
    private fun buscar(nombre : String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "SELECT * FROM CLIENTE WHERE NOMBRE= '"+nombre+"'"

            var respuesta = transaccion.rawQuery(SQL, null)
            if (respuesta.moveToFirst()==true) {
                var TraeID = respuesta.getString(0)
                var Traecantidad = respuesta.getString(1)
                var Traeprecio = respuesta.getString(2)

                text_id?.setText(TraeID)
                text_nombre?.setText(Traecantidad)
                text_domicilio?.setText(Traeprecio)
                transaccion.close()
            }
        } catch (err: SQLiteException) {
            mensaje("ERROR", "AL PARECER NO EXISTE LA DESCRIPCION")
        }
    }

    fun elimina(nombre: String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "DELETE FROM CLIENTE WHERE NOMBRE= '"+nombre+"'"
            transaccion.execSQL(SQL)
            transaccion.close()
            limpiar()
            Toast.makeText(this, "Cliente Eliminado", Toast.LENGTH_LONG).show()
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
        text_nombre?.setText("")
        text_id?.setText("")
        text_domicilio?.setText("")
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
