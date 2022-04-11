package com.example.salman.chemicalengineeringamu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;


public class SubjectList extends AppCompatActivity {


//    public static String[] language1 = {"Heat Transfer Operation#5903#B.Tech. Chemical III Sem#5937", "Mass Transfer Operation", "Chemical Reaction Engineering", "Economics & Management", "Equillibrium Stage Processes", "Seminar And Report Writing",
//            "Computer Application Lab-2", "Chemical Process Industries 1", "Chemical Process Industries 2", "Transport Phenomena", "Process Equipment Design",
//            "Department Electiv-1", "Chemical Engineering Design", "Unit Operation Lab-2", "Reaction Engineering & Thermodynamics Lab"
//    };
//
//    public static String[] language2 = {"Energy Engineering & Management", "Process & Dynamics Control", "Process Engineering & Plant Design - 1", "Department Elective ",
//            "Open Elective", "CAD/Simulation Lab", "Energy & Technology Lab", "Summer Training", "Project",
//            "Modelling,Simulation & Optimization", "Process Engineering & Plant Design-2", "Department Elective-3", "Department Elective"
//            , "Open Elective", "Process Control & Instrumentation Lab", "Project"
//
//    };
//    public static String[] language = {"Fluid Mechanics", "Fluid Particle Operation", "Higher Mathematics", "Electrical Engineering", "Basic Principle of Chemical engineering",
//            "Computer Application Lab-1", "Machine Design & computer Graphics", "Electrical Engineering Lab", "Engineering Chemistry & Material Science"
//            , "Engineering Biology", "Process Instrumentation", "Chemical Thermodynamics",
//            "Numerical Methods in Chemical Engineering", "Unit Operation Lab-1", "Communication Skill Lab"
//    };

    private ArrayList<String> itemName = null;
    public static int position_year = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.second_year );
        position_year = getIntent().getIntExtra( "salman", 0 );
//        if(position_year == 0){
            itemName = createList(MainActivity.mainOArrayList);
//        }
//       else if(position_year == 1){
//            itemName = language1;
//        }
//       else if(position_year == 2){
//            itemName = language2;
//        }

        initViews();

    }

    private ArrayList<String> createList(ArrayList<MainOptionData> arrayList){
        ArrayList<String> stringArrayList = new ArrayList<>();
        for(int i = 0 ; i < arrayList.size() ; i ++){
            stringArrayList.add(arrayList.get(i).getSubjectName());
        }
        return stringArrayList;
    }


    private void initViews() {
        RecyclerView myrecycleview = (RecyclerView) findViewById( R.id.myrecycler_view );
        myrecycleview.setHasFixedSize( true );
        myrecycleview.setLayoutManager( new LinearLayoutManager( this ) );
//        position_year = getIntent().getIntExtra( "salman", 0 );
//        if (position_year == 0) {
//            myrecycleview.setAdapter( new DataAdapter(language) );
//        }
//        else if (position_year == 1)
//            myrecycleview.setAdapter( new DataAdapter( language1 ) );
//        else if (position_year == 2)
        myrecycleview.setAdapter( new DataAdapter( itemName ) );
        myrecycleview.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), myrecycleview, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                    Intent fixture = new Intent( SubjectList.this, Material.class );
                    fixture.putExtra( "subject_position", position );
                    fixture.putExtra( "position_year", position_year );
                    startActivity( fixture );

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


}











