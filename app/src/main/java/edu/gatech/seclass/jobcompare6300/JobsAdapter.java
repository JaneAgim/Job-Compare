package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.apis.Apis;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.dao.utils.JobsContract;
import edu.gatech.seclass.jobcompare6300.models.Job;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {
    private Context jContext;
    private List<Job> jJobList;
    private Job job;
    private Apis jJobsApis;
    private RadioButton lastCheckedRB1 = null;
    private RadioButton lastCheckedRB2 = null;
    public Job compareJob1;
    public Job compareJob2;



    public JobsAdapter(Context context, Apis jobsApis) {
        jContext = context;
        jJobsApis = jobsApis;

    }
    public class JobsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView j1Text;
        public TextView n1Text;
        public TextView j2Text;
        public RadioButton j1Check;
        public RadioButton j2Check;
        public TextView id;

        public JobsViewHolder(View itemView) {
            super(itemView);

            j1Text = itemView.findViewById(R.id.scoreID_item);
            n1Text = itemView.findViewById(R.id.titleID1_item);
            itemView.setOnClickListener(this);
            j2Text = itemView.findViewById(R.id.companyID_item);



        }
        @Override
        public void onClick(View v) {
        }

    }


    @Override
    public JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(jContext);
        View view = inflater.inflate(R.layout.jobs_item, parent, false);
        return new JobsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobsAdapter.JobsViewHolder holder, int position) {


        List<Job> sortedJobs = jJobsApis.getAllJobs();
        //System.out.println(sortedJobs.get(0));
        job = sortedJobs.get(position);
        String name1;
        if (job.isCurrentJob()){
            name1 = job.getTitle() + "(Current )";
        } else{
            name1 = job.getTitle();
        }

        String name2 = job.getCompany();
        String name3 = String.valueOf(job.getScore());
        Integer.toString((int)(job.getScore()));
        Integer jID = job.getId();
        //String name3 =
        holder.j1Text.setText(name3);
        holder.n1Text.setText(name1);
        holder.j2Text.setText(name2);

            //holder.j3Text.setText(name3);




    }
    @Override
    public int getItemCount() {
        return jJobsApis.getAllJobs().size();
    }







}
