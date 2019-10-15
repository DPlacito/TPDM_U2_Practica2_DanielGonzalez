package mx.edu.ittepic.tpdm_u2_practica2_danielgonzalez

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_insertar_almacen.*

class Insertar_almacen : AppCompatActivity() {

    private var edit_producto : EditText?=null
    private var edit_cantidad : EditText?=null
    private var edit_precio : EditText?=null
    private var boton_insertar : Button?=null
    var basedatos = BaseDatos(this, "practica2", null, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_almacen)
        setSupportActionBar(cabezera_Insertar_Almacen)
        title = "Insertar_Almacen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edit_producto = findViewById(R.id.edit_Producto)
        edit_cantidad = findViewById(R.id.edit_Cantidad)
        edit_precio = findViewById(R.id.edit_Precio)
        boton_insertar = findViewById(R.id.boton_Insertar_Almacen)

    boton_insertar?.setOnClickListener {
        insertar()
    }
    }
    private fun validarCampos(): Boolean {
        if (edit_producto?.text.toString().isEmpty() || edit_cantidad?.text.toString().isEmpty() || edit_precio?.text.toString().isEmpty()) {
            return false
        } else {
            return true
        }
    }

    private fun insertar(){
        try {
            var transaccion = basedatos.writableDatabase
            var SQL = "INSERT INTO ALMACEN VALUES(NULL,'PRODUCTO','CANTIDAD','PRECIO')"

            if (validarCampos() == false) {
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")

            } else {
                SQL = SQL.replace("PRODUCTO", edit_producto?.text.toString())
                SQL = SQL.replace("CANTIDAD", edit_cantidad?.text.toString())
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
        edit_producto?.setText("")
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
