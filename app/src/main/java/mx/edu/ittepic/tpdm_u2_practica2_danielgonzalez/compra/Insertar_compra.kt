package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.compra

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_insertar_compra.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.BaseDatos
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Insertar_compra : AppCompatActivity() {

    private var edit_fecha : EditText?=null
    private var edit_total : EditText?=null
    private var boton_insertar : Button?=null
    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_compra)
        setSupportActionBar(cabezera_Insertar_Compra)
        title = "Insertar Compra"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edit_fecha = findViewById(R.id.edit_Fecha_Compra)
        edit_total = findViewById(R.id.edit_Total_Compra)
        boton_insertar = findViewById(R.id.boton_Insertar_Compra)

        boton_insertar?.setOnClickListener {
            insertar()
        }
    }
    private fun validarCampos(): Boolean {
        if (edit_fecha?.text.toString().isEmpty() || edit_total?.text.toString().isEmpty()) {
            return false
        } else {
            return true
        }
    }

    private fun insertar(){
        try {
            var transaccion = basedatos.writableDatabase
            var SQL = "INSERT INTO COMPRA VALUES(NULL,'FECHA','PRECIO','ID_EMPRESA')"

            if (validarCampos() == false) {
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")

            } else {
                SQL = SQL.replace("FECHA", edit_fecha?.text.toString())
                SQL = SQL.replace("PRECIO", edit_total?.text.toString())

                transaccion.execSQL(SQL)
                transaccion.close()//CIERRA LA TRANSACCION
                mensaje("EXITO", "SE INSERTO CORRECTAMENTE")
            }
        } catch (err: SQLiteException) {
            mensaje("Error", "NO SE PUDO INSERTAR")
        }
        limpiar()
    }

    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("Ok") { dialog, whitch -> }
            .show()
    }

    fun limpiar() {
        edit_fecha?.setText("")
        edit_total?.setText("")
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