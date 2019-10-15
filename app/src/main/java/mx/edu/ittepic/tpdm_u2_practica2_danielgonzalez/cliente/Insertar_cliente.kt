package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.cliente

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_insertar_cliente.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.BaseDatos
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Insertar_cliente : AppCompatActivity() {

    private var edit_nombre: EditText? = null
    private var edit_domicilio: EditText? = null
    private var boton_insertar: Button? = null
    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_cliente)

        setSupportActionBar(cabezera_Insertar_Clientes)
        title = "Insertar_Clientes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edit_nombre = findViewById(R.id.edit_Nombre)
        edit_domicilio = findViewById(R.id.edit_Domicilio)
        boton_insertar = findViewById(R.id.boton_Insertar_Cliente)
        boton_insertar?.setOnClickListener {
            insertar()
        }
    }

    private fun validarCampos(): Boolean {
        if (edit_nombre?.text.toString().isEmpty() || edit_domicilio?.text.toString().isEmpty()) {
            return false
        } else {
            return true
        }
    }

    private fun insertar() {
        try {
            var transaccion = basedatos.writableDatabase
            var SQL = "INSERT INTO CLIENTE VALUES(NULL,'NOMBRE','DOMICILIO','ID_EMPRESA')"

            if (validarCampos() == false) {
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")

            } else {
                SQL = SQL.replace("NOMBRE", edit_nombre?.text.toString())
                SQL = SQL.replace("DOMICILIO", edit_domicilio?.text.toString())

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
        edit_nombre?.setText("")
        edit_domicilio?.setText("")
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
