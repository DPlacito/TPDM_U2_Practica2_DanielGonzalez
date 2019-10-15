package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.empresa

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_insertar_empresa.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.BaseDatos
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Insertar_empresa : AppCompatActivity() {
    private var edit_descripcion : EditText?=null
    private var edit_domicilio : EditText?=null
    private var boton_insertar : Button?=null
    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_empresa)
        setSupportActionBar(cabezera_Insertar_Empresa)
        title = "Insertar_Empresa"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edit_descripcion = findViewById(R.id.edit_Descripcion_E)
        edit_domicilio = findViewById(R.id.edit_Domicilio_E)
        boton_insertar = findViewById(R.id.boton_Insertar_Empresa)

        boton_insertar?.setOnClickListener {
            insertar()
        }
    }
    private fun validarCampos(): Boolean {
        if (edit_descripcion?.text.toString().isEmpty() || edit_domicilio?.text.toString().isEmpty()) {
            return false
        } else {
            return true
        }
    }

    private fun insertar(){
        try {
            var transaccion = basedatos.writableDatabase
            var SQL = "INSERT INTO EMPRESA VALUES(NULL,'DESCRIPCION','DOMICILIO')"

            if (validarCampos() == false) {
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")

            } else {
                SQL = SQL.replace("DESCRIPCION", edit_descripcion?.text.toString())
                SQL = SQL.replace("DOMICILIO", edit_domicilio?.text.toString())

                transaccion.execSQL(SQL)
                transaccion.close()//CIERRA LA TRANSACCION
                mensaje("EXITO", "SE INSERTO CORRECTAMENTE")
                limpiar()
            }
        } catch (err: SQLiteException) {
            mensaje("Error", "NO SE PUDO INSERTAR")
        }

    }

    fun mensaje(t: String, m: String) {
        AlertDialog.Builder(this).setTitle(t).setMessage(m)
            .setPositiveButton("Ok") { dialog, whitch -> }
            .show()
    }

    fun limpiar() {
        edit_domicilio?.setText("")
        edit_descripcion?.setText("")
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

