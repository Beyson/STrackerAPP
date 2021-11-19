package com.example.ssis_tracker.view.performance;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.presenter.performance.PerformanceFragmentPresenter;
import com.example.ssis_tracker.presenter.performance.PerformanceFragmentPresenterImpl;
import java.util.ArrayList;

public class PerformanceFragment extends Fragment implements PerformanceFragmentView {

    private TextView textViewPorcentajeRegistrados,textViewPorcentajeTerminados,textViewPorcentajeEficiencia,
                     textViewPorcentajeTiempo,textViewPorcentajeAtrasados;
    private View view;
    private SwipeRefreshLayout SwipeRefreshLayoutPerformance;
    private PerformanceFragmentPresenter performanceFragmentPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.view = inflater.inflate(R.layout.fragment_performance, container, false);
        this.performanceFragmentPresenter   = new PerformanceFragmentPresenterImpl(this);
        this. SwipeRefreshLayoutPerformance = this.view.findViewById(R.id. SwipeRefreshLayoutPerformance);
        this.textViewPorcentajeRegistrados  = this.view.findViewById(R.id.textViewPorcentajeRegistrados);
        this.textViewPorcentajeTerminados   = this.view.findViewById(R.id.textViewPorcentajeTerminados);
        this.textViewPorcentajeEficiencia   = this.view.findViewById(R.id.textViewPorcentajeEficiencia);
        this.textViewPorcentajeTiempo       = this.view.findViewById(R.id.textViewPorcentajeTiempo);
        this.textViewPorcentajeAtrasados    = this.view.findViewById(R.id.textViewPorcentajeAtrasados);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Desempeño");
        SolicitarDatosPerformance();
        this.SwipeRefreshLayoutPerformance.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SolicitarDatosPerformance();
            }
        });
        return view;
    }

    public void animateTextView(final TextView textView, float count, final boolean showPercent){
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, Math.round( count ));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                if(showPercent){
                    textView.setText(String.valueOf(animation.getAnimatedValue())+"%");
                }else{
                    textView.setText(String.valueOf(animation.getAnimatedValue()));
                }
            }
        });
        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round(startValue + (endValue - startValue) * fraction);
            }
        });
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    public void SolicitarDatosPerformance() {
        this.SwipeRefreshLayoutPerformance.setRefreshing(true);
        this.performanceFragmentPresenter.SolicitarDatosPerformance();
    }

    @Override
    public void PresentarDatosPerformance(ArrayList<Float> performances) {

        SwipeRefreshLayoutPerformance.setRefreshing(false);
        this.textViewPorcentajeRegistrados.setText(String.valueOf(  performances.get(0) ));
        this.textViewPorcentajeTerminados .setText(String.valueOf(  performances.get(1) ));
        this.textViewPorcentajeEficiencia .setText(String.valueOf(  performances.get(2) ));
        this.textViewPorcentajeTiempo     .setText(String.valueOf(  performances.get(3) ));
        this.textViewPorcentajeAtrasados  .setText(String.valueOf(  performances.get(4) ));

        animateTextView(this.textViewPorcentajeRegistrados,  performances.get(0)  ,false);
        animateTextView(this.textViewPorcentajeTerminados ,  performances.get(1)  ,false);
        animateTextView(this.textViewPorcentajeEficiencia ,  performances.get(2)  ,true);
        animateTextView(this.textViewPorcentajeTiempo     ,  performances.get(3)  ,false);
        animateTextView(this.textViewPorcentajeAtrasados  ,  performances.get(4)  ,false);
    }

    @Override
    public void MostarMensaje(String mensaje) {
        SwipeRefreshLayoutPerformance.setRefreshing(false);
        Snackbar.make(this.view , mensaje , Snackbar.LENGTH_LONG).show();
    }
}
