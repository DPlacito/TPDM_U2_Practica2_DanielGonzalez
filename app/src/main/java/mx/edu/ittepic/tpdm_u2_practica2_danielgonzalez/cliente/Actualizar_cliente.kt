package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.cliente

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_actualizar_cliente.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.BaseDatos
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Actualizar_cliente : AppCompatActivity() {

    private var edit_buscar_actualizar: EditText? = null
    private var edit_actualiza_nombre: EditText? = null
    private var edit_actualizar_domicilio: EditText? = null
    private var boton_actualizar: Button? = null
    private var boton_buscar: Button? = null

    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_cliente)
        setSupportActionBar(cabezera_Actualizar_C)
        title = "Actualizar_Cliente"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edit_buscar_actualizar = findViewById(R.id.edit_Buscar_Actualizar_C)
        edit_actualiza_nombre = findViewById(R.id.edit_Nombre_Actualizar_C)
        edit_actualizar_domicilio = findViewById(R.id.edit_Domicilio_Actualizar_C)
        boton_actualizar = findViewById(R.id.boton_Actualizar_C)
        boton_buscar = findViewById(R.id.boton_Buscar_Actualizar_C)

        boton_buscar?.setOnClickListener {
            buscar(edit_buscar_actualizar?.text.toString())
        }

        boton_actualizar?.setOnClickListener {
            aplicarActualizar()
        }
    }

    private fun buscar(nombre: String) {
        try {
            var transaccion = basedatos.readableDatabase
            var SQL = "SELECT * FROM CLIENTE WHERE NOMBRE= '" + nombre +"'"

            var respuesta = transaccion.rawQuery(SQL, null)
            if (respuesta.moveToFirst() == true) {
                var Traenombre = respuesta.getString(1)
                var Traedomicilio = respuesta.getString(2)

                edit_actualiza_nombre?.setText(Traenombre)
                edit_actualizar_domicilio?.setText(Traedomicilio)
                transaccion.close()
            }
        } catch (err: SQLiteException) {
            mensaje("ERROR", "AL PARECER NO EXISTE EL NOMBRE")
        }
    }

    private fun aplicarActualizar() {
        try {
            var transaccion = basedatos.writableDatabase
            var SQL =
                "UPDATE CLIENTE SET  NOMBRE ='CAMPONOMBRE', DOMICILIO ='CAMPODOMICILIO' WHERE ID_CLIENTE=ID_CLIENTE "

            if (validarCampos() == false) {
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")
                return
            }
            SQL = SQL.replace("CAMPONOMBRE", edit_actualiza_nombre?.text.toString())
            SQL = SQL.replace("CAMPODOMICILIO", edit_actualizar_domicilio?.text.toString())

            transaccion.execSQL(SQL)
            transaccion.close()//CIERRA LA TRANSACCION
            mensaje("ACTUALIZADO", "SE ACTUALIZO CORRECTAMENTE")
            limpiar()
        } catch (err: SQLiteException) {
            mensaje("Error", "NO SE PUDO INSERTAR, TAL VEZ ID YA EXISTE")
        }
    }

    fun validarCampos(): Boolean {
        if (edit_actualiza_nombre?.text.toString().isEmpty() || edit_actualizar_domicilio?.text.toString().isEmpty()) {
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
        edit_actualiza_nombre?.setText("")
        edit_actualizar_domicilio?.setText("")
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
