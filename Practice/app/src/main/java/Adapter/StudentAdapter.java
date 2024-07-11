package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;

import java.util.List;

import model.Student;

public class StudentAdapter extends
        RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public static class StudentViewHolder extends
            RecyclerView.ViewHolder {
        public TextView textViewId;
        public TextView textViewName;
        public TextView textViewEmail;
        public ImageButton editButton;
        public ImageButton deleteButton;
        public StudentViewHolder(View itemView, final
        OnItemClickListener listener) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.text_view_id);
            textViewName =
                    itemView.findViewById(R.id.text_view_student_name);
            textViewEmail =itemView.findViewById(R.id.text_view_student_email);
            deleteButton = itemView.findViewById(R.id.button_delete);
            deleteButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }
    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,
                        parent, false);
        return new StudentViewHolder(itemView, listener);
    }
    @Override
    public void onBindViewHolder(StudentViewHolder holder, int
            position) {
        Student currentCourse = studentList.get(position);
        holder.textViewId.setText(currentCourse.getId());
        holder.textViewName.setText(currentCourse.getName());

        holder.textViewEmail.setText(currentCourse.getEmail());
    }
    @Override
    public int getItemCount() {
        return studentList.size();
    }
}