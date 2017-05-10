package aiproj.td.Feature;

import aiproj.td.SliderBoard.SliderBoard;

/**
 * Created by daewin on 0004, May, 4.
 */
public interface Feature {

    /**
     * Returns an evaluation of the current board state based on the goal of the implementation.
     * @return
     */
    float evaluate(SliderBoard board);

    void setWeight(float weight);

    float getWeight();
}
