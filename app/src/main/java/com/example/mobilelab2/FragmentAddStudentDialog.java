package com.example.mobilelab2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAddStudentDialog extends DialogFragment {

    EditText editStudentName;
    EditText editStudentMark;
    EditText editStudentId;

    APIInterface apiInterface;

    Context parentContext;

    public void setParentContext(Context context){
        this.parentContext = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_student_dualog_layout, null);
        builder.setView(view);

        editStudentName = view.findViewById(R.id.fg_add_student_name);
        editStudentMark = view.findViewById(R.id.fg_add_student_mark);
        editStudentId = view.findViewById(R.id.fg_add_student_id);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        builder.setMessage(R.string.add_student_dialog_header);
        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Integer studentId = 0;
                try {
                    studentId = Integer.parseInt(editStudentId.getText().toString());
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Введите целое число", Toast.LENGTH_SHORT).show();
                }
                String studentName = editStudentName.getText().toString();
                String studentMark = editStudentMark.getText().toString();

                Call<Student> call = apiInterface.createStudent(new Student(studentName, studentMark, studentId));
                call.enqueue(new Callback<Student>() {

                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        Toast.makeText(parentContext, "Студент добавлена", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {
                        Toast.makeText(getContext(), "Не удалось добавить", Toast.LENGTH_SHORT).show();
                    }
                });

                System.out.println("Добавить" + studentId + "/" + studentName + "/" + studentMark);
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println("Отмена");
            }
        });


        return builder.create();
    }
}
