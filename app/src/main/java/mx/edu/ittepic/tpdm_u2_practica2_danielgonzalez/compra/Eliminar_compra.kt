package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.compra

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
import kotlinx.android.synthetic.main.activity_eliminar_compra.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.BaseDatos
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Eliminar_compra : AppCompatActivity() {
    private var edit_buscar : EditText?=null
    private var text_id : TextView?=null
    private var text_fecha : TextView?=null
    private var text_total : TextView?=null
    private var boton_eliminar : Button?=null
    private var boton_buscar : Button?=null

    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_compra)
        setSupportActionBar(cabezera_Eliminar_Compra)
        title = "Eliminar Compra"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        boton_buscar = findViewById(R.id.boton_Buscar_Eliminar_Compra)
        boton_eliminar = findViewById(R.id.boton_Eliminar_Compra)
        edit_buscar = findViewById(R.id.edit_Buscar_Eliminar_Compra)
        text_id = findViewById(R.id.text_ID_Compra)
        text_fecha= findViewById(R.id.text_Fecha_Compra)
        text_total = findViewById(R.id.text_Total_Compra)

        boton_buscar?.setOnClickListener {
            buscar(edit_buscar?.text.toString())
        }

        boton_eliminar?.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("CUIDADO!!")
            dialog.setMessage("Estas Seguro De Que Quieres Eliminar Esta  Compra ?")
            dialog.setPositiveButton("Si Eliminar") { _: DialogInterface, _: Int ->
                elimina(text_id?.text.toString())
            }
            dialog.setNeutralButton("Cancelar") { _: DialogInterface, _: Int ->

            }
            dialog.show()
            //
        }

    }
    private fun buscar(ID : String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "SELECT * FROM COMPRA WHERE ID_COMPRA= "+ID

            var respuesta = transaccion.rawQuery(SQL, null)
            if (respuesta.moveToFirst()==true) {
                var TraeID = respuesta.getString(0)
                var Traefecha = respuesta.getString(1)
                var Traetotal = respuesta.getString(2)

                text_id?.setText(TraeID)
                text_fecha?.setText(Traefecha)
                text_total?.setText(Traetotal)
                transaccion.close()
            }
        } catch (err: SQLiteException) {
            mensaje("ERROR", "AL PARECER NO EXISTE LA DESCRIPCION")
        }
    }

    fun elimina(cantidad: String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "DELETE FROM COMPRA WHERE ID_COMPRA= "+cantidad
            transaccion.execSQL(SQL)
            transaccion.close()
            limpiar()
            Toast.makeText(this, "Compra Eliminada", Toast.LENGTH_LONG).show()
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
        text_fecha?.setText("")
        text_id?.setText("")
        text_total?.setText("")
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