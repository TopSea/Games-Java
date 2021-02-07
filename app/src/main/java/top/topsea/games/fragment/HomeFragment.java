package top.topsea.games.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import top.topsea.games.database.GamesDatabase;
import top.topsea.games.database.Score;
import top.topsea.games.database.ScoreDao;
import top.topsea.games.game2048.Array2048;
import top.topsea.games.gameRoad.ArrayRoad;
import top.topsea.games.utils.DatabaseUtils;
import top.topsea.games.utils.FileOperation;
import top.topsea.games.R;

public class HomeFragment extends Fragment {

    //database
    private GamesDatabase database;
    private ScoreDao scoreDao;
    private Score scoreD;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        database = GamesDatabase.getInstance(getContext());
        scoreDao = database.getScoreDao();
        if (scoreDao.getScore("Road") == null){
            Log.d("///////////////", "insert");
            DatabaseUtils.InitData().forEach(score -> {
                scoreDao.insertScore(score);
            });
        }

        getView().findViewById(R.id.home_button_2048).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            File file = new File(getContext().getFilesDir(), "2048.array");
            if (!file.exists()){
                FileOperation.saveArray(getContext(), Array2048.InitializeArray(4, 4), "2048.array");
            }
            navController.navigate(R.id.action_homeFragment_to_main2048Fragment);
        });

        getView().findViewById(R.id.home_button_tetris).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            File file = new File(getContext().getFilesDir(), "tetris.array");
            if (!file.exists()){
                FileOperation.saveArray(getContext(), Array2048.InitializeArray(4, 4), "tetris.array");
            }
            navController.navigate(R.id.action_homeFragment_to_fragment2048);
        });

        getView().findViewById(R.id.home_button_road).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            File file = new File(getContext().getFilesDir(), "road.array");
            if (!file.exists()){
                FileOperation.saveArray(getContext(), ArrayRoad.InitializeArray(), "road.array");
            }
            navController.navigate(R.id.action_homeFragment_to_roadFragment);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}