package com.example.mobilelab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment2 extends Fragment implements Fragment2Adapter.ItemClickListener{
    Fragment2Adapter adapter;
    List<Student> items = new ArrayList<>();

    Button addNewStudent;
    Button refreshStudentList;

    APIInterface apiInterface;

    Fragment2(){
        super(R.layout.fragment2_layout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment2_layout, container, false);

        addNewStudent = rootView.findViewById(R.id.f2_bac);
        refreshStudentList = rootView.findViewById(R.id.f2_brc);

        refreshStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        addNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentAddStudentDialog addStudentFragment = new FragmentAddStudentDialog();
                addStudentFragment.setParentContext(getContext());
                addStudentFragment.show(getActivity().getSupportFragmentManager(), "ADD_T}STUDENT");
            }
        });

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new Fragment2Adapter(this.getContext(), items);
        adapter.setmClickListener(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Student student = items.get(position);
        System.out.println("ID: " + student.id + " Имя: " + student.name + " Оценка: " + student.mark);
        Toast.makeText(getActivity(),"ID: " + student.id + " Имя: " + student.name + " Оценка: " + student.mark, Toast.LENGTH_SHORT).show();
    }

    public void getData(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<Student>> call = apiInterface.getStudentList();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students= response.body();
                items.clear();
                items.addAll(students);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(getActivity(), "Невозможность соединиться с сервеоом", Toast.LENGTH_SHORT).show();
            }
        });
    }
}