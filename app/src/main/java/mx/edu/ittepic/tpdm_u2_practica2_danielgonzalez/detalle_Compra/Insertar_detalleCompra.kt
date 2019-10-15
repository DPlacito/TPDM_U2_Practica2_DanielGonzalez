package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.detalle_Compra

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_insertar_detalle_compra.*
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.BaseDatos
import mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez.R

class Insertar_detalleCompra : AppCompatActivity() {

    private var edit_cantidad : EditText?=null
    private var edit_precio : EditText?=null
    private var boton_insertar : Button?=null
    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_detalle_compra)
        setSupportActionBar(cabezera_Insertar_DC)
        title = "Insertar_Detalle Compra"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edit_cantidad = findViewById(R.id.edit_Cantidad_DC)
        edit_precio = findViewById(R.id.edit_Precio_DC)
        boton_insertar = findViewById(R.id.boton_Insertar_DC)

        boton_insertar?.setOnClickListener {
            insertar()
        }
    }
    private fun validarCampos(): Boolean {
        if (edit_cantidad?.text.toString().isEmpty() || edit_precio?.text.toString().isEmpty()) {
            return false
        } else {
            return true
        }
    }

    private fun insertar(){
        try {
            var transaccion = basedatos.writableDatabase
            var SQL = "INSERT INTO DETALLECOMPRA VALUES(NULL,'FECHA','PRECIO','ID_ALMACEN')"

            if (validarCampos() == false) {
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")

            } else {
                SQL = SQL.replace("FECHA", edit_cantidad?.text.toString())
                SQL = SQL.replace("PRECIO", edit_precio?.text.toString())

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
        edit_cantidad?.setText("")
        edit_precio?.setText("")
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
