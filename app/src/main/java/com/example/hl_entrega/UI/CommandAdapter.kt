import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.hl_entrega.Models.Command
import com.example.hl_entrega.R

class CommandAdapter(context: Context, commands: List<Command>) :
    ArrayAdapter<Command>(context, 0, commands) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val viewHolder: ViewHolder

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_command, parent, false)
            viewHolder = ViewHolder()
            viewHolder.textViewCommandData = itemView.findViewById(R.id.text_view_command_data)
            viewHolder.textViewCommandDescription = itemView.findViewById(R.id.text_view_command_description)
            itemView.tag = viewHolder
        } else {
            viewHolder = itemView.tag as ViewHolder
        }

        val currentCommand = getItem(position) as Command
        viewHolder.textViewCommandData.text = "Data: ${currentCommand.dataC}"
        viewHolder.textViewCommandDescription.text = currentCommand.descriptionC

        return itemView!!
    }

    private class ViewHolder {
        lateinit var textViewCommandId: TextView
        lateinit var textViewCommandData: TextView
        lateinit var textViewCommandDescription: TextView
    }
}
