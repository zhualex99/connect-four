# connect-four
This game is classic connect four, where the user plays against the computer with both sides trying to place 4 of their pieces adjacent or diagonal to eachother in order to win!

Options:
ln 13 Main.java: toggle AI_ACTIVE to play with the ai (true - ai activated, false - deactivated)

ln 15 Main.java: change DEPTH to change the minimum depth in which the ai looks into the future (keep this <= 10)

ln 6 AI.java: change MINTIME to change the minimum time that the ai will spend looking into the future (it will make moves instantly if it has already solved the position)

ln 5-8 InstanPosEval.java: change the weightings to change how the ai evaluates a position.
