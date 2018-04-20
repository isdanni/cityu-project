#include<iostream>
#include<cstdlib>
#include<ctime>

using namespace std;

class TicTacToe {
public:
	TicTacToe();
	void printBoard();
	void place_chess();
	int isOver(); //whether this game has ended
	bool computerWin(); //whether the computer has won the game
	bool humanWin(); //whether the human player has won the game
private:
	int** generateAllChoices();
	char next();
	char chess[3][3]; //the whole checkerboard
	int step; // the number of chesses on the checkerboard
	static const int computerOrder = 2; //the computer is the first player or the second player
	void place_chess(char who, int choice[]);
	int isGoodChoice(int choice[]); //check whether the choice of the computer is a good choice
	void computerPlace(); //let the computer place chess
	void humanPlace(); //let the human player place chess
	bool isChoiceValid(int choice[]); //whether the choice of the human player is valid
	
};


TicTacToe::TicTacToe(){
	for(int i=0; i<3; i++){
		for(int n=0; n<3; n++){
			chess[i][n] = ' ';
		}
	}
	step = 0;
}

void TicTacToe::printBoard(){
	cout << endl << endl;
	for (int i=0; i<3; i++){
		cout << "|-" << i+1 << '-';
	}
	cout << '|' << endl;
	for (int i=0; i<3; i++){
		cout << i+1;
		for(int n=0; n<3; n++){
			cout << " " << chess[i][n] <<" |";
		}
		cout << endl;
		cout << "|---|---|---|" << endl;
	}
	cout << endl;
	cout << "PLAY1 : x" << endl;
	cout << "PLAY2 : o" << endl;
	cout << endl << endl;
}

//every step
void TicTacToe::place_chess(){
	if(next() == 'c'){
		cout << "It is my turn." << endl;
		computerPlace();
	}else{
		cout << "It is your turn." << endl;
		humanPlace();
	}
	printBoard();
}

//place the chess and add to value
void TicTacToe::place_chess(char who, int choice[]){
	if (who == 'c'){
		if(!(computerOrder - 1)){
			chess[choice[0]][choice[1]] = 'x';
		}else{
			chess[choice[0]][choice[1]] = 'o';
		}
	}else if(who == 'h'){
		if(!(computerOrder - 1)){
			chess[choice[0]][choice[1]] = 'o';
		}else{
			chess[choice[0]][choice[1]] = 'x';
		}
	}
	step++;
}

int** TicTacToe::generateAllChoices(){
	int** choice_list = new int*[9];
	for(int i=0; i<9; i++){
		choice_list[i] = new int[2];
		for(int n=0; n<2; n++){
			choice_list[i][n]=4;
		}
	}
	for(int i=0; i<3;i++){
		for(int n=0; n<3;n++){
			if(chess[i][n]==' '){
				for(int j=0; j<9; j++){
					if(choice_list[j][0] == 4){
						choice_list[j][0] = i;
						choice_list[j][1] = n;
						break;
					}
				}
			}
		}
	}
	return choice_list;
}

int TicTacToe::isGoodChoice(int choice[]){
	char tempChess[3][3];
	int tempStep = step;
	for(int i=0; i<3; i++){
		for(int n=0; n<3; n++){
			tempChess[i][n] = chess[i][n];
		}
	}
	if(next()=='c'){
		place_chess('c',choice);
		if(computerWin()){
			step = tempStep;
			for(int i=0; i<3; i++){
				for(int n=0; n<3; n++){
					chess[i][n] = tempChess[i][n];
				}
			}
			return 1;
		}else if(step==9){
			step = tempStep;
			for(int i=0; i<3; i++){
				for(int n=0; n<3; n++){
					chess[i][n] = tempChess[i][n];
				}
			}
			return 0;
		}else{
			int** choices = generateAllChoices();
			for(int i=0; i<9 && choices[i][0] != 4;i++){
				if(isGoodChoice(choices[i])== -1){
					step = tempStep;
					for(int i=0; i<3; i++){
						for(int n=0; n<3; n++){
							chess[i][n] = tempChess[i][n];
						}
					}
					return -1;
				}
			}
			for(int i=0; i<9 && choices[i][0] != 4;i++){
				if(isGoodChoice(choices[i])== 0){
					step = tempStep;
					for(int i=0; i<3; i++){
						for(int n=0; n<3; n++){
							chess[i][n] = tempChess[i][n];
						}
					}
					return 0;
				}
			}
			step = tempStep;
			for(int i=0; i<3; i++){
				for(int n=0; n<3; n++){
					chess[i][n] = tempChess[i][n];
				}
			}
			return 1;
		}
	}else{
		place_chess('h',choice);
		if(humanWin()){
			step = tempStep;
			for(int i=0; i<3; i++){
				for(int n=0; n<3; n++){
					chess[i][n] = tempChess[i][n];
				}
			}
			return -1;
		}else if(step == 9){
			step = tempStep;
			for(int i=0; i<3; i++){
				for(int n=0; n<3; n++){
					chess[i][n] = tempChess[i][n];
				}
			}
			return 0;
		}else{
			int** tempChoices = generateAllChoices();
			for(int i=0; i<9 && tempChoices[i][0] != 4;i++){
				if(isGoodChoice(tempChoices[i])== 1){
					step = tempStep;
					for(int i=0; i<3; i++){
						for(int n=0; n<3; n++){
							chess[i][n] = tempChess[i][n];
						}
					}
					return 1;
				}
			}
			for(int i=0; i<9 && tempChoices[i][0] != 4; i++){
				if(isGoodChoice(tempChoices[i]) == 0){
					step = tempStep;
					for(int i=0; i<3; i++){
						for(int n=0; n<3; n++){
							chess[i][n] = tempChess[i][n];
						}
					}
					return 0;
				}
			}
			step = tempStep;
			for(int i=0; i<3; i++){
				for(int n=0; n<3; n++){
					chess[i][n] = tempChess[i][n];
				}
			}
			return -1;
		}
	}
}

//0:computer will lose, 1:may be a tie or win 
void TicTacToe::computerPlace(){
	int** choice = generateAllChoices();
	for(int i=0; i<9; i++){
		if(isGoodChoice(choice[i])==1){
			place_chess('c',choice[i]);
			return;
		}
	}
	for(int i=0; i<9; i++){
		if(isGoodChoice(choice[i])==0){
			place_chess('c', choice[i]);
			return;
		}
	}
	place_chess('c', choice[0]);
}

void TicTacToe::humanPlace(){
	int choice[2];
	do{
		cout << "Please input two integers(1-3) to represent the row and column number respectively." << endl;
		cin >> choice[0] >> choice[1];
	}while(!isChoiceValid(choice));
	choice[1]--;
	choice[0]--;
	place_chess('h', choice);
}

bool TicTacToe::isChoiceValid(int choice[]){
	if(chess[choice[0]-1][choice[1]-1] != ' '){
		cout << "Choices already filled!" << endl;
		return false;
	}else if(choice[0] > 3 || choice[0] < 1 || choice[1] > 3 || choice[1] < 1){
		cout << "THe location should be intergers in [1,3]" << endl;
		return false;
	}
	return true;
}

//bool - whether the computer wins
bool TicTacToe::computerWin(){
	int rowSum[3] = {}, columnSum[3] = {};
	int diagonal1 = 0 , diagonal2 = 0;
	char computerChess = ' ';

	if(computerOrder == 1)
		computerChess = 'x';
	else
		computerChess = 'o';

	for(int i=0; i<3; i++){
		for(int n=0; n<3; n++){
			if(chess[i][n] == computerChess){
				rowSum[i] ++;
				columnSum[n] ++;
				if(i==n)
					diagonal1 ++;
				else if(i+n ==4)
					diagonal2 ++;
			}
		}
	}
	if(diagonal2 == 3 || diagonal1 == 3){
			return true;
		}else{
			for(int i=0; i<3; i++){
				if(rowSum[i]==3 || columnSum[i] ==3){
					return true;
				}
			}
		}

	return false;
}

//whether the human player wins
bool TicTacToe::humanWin(){
	int rowSum[3] = {}, columnSum[3] = {};
	int diagonal1 = 0 , diagonal2 = 0;
	char humanChess = ' ';

	if(computerOrder == 1)
		humanChess = 'o';
	else
		humanChess = 'x';

	for(int i=0; i<3; i++){
		for(int n=0; n<3; n++){
			if(chess[i][n] == humanChess){
				rowSum[i] ++;
				columnSum[n] ++;
				if(i==n)
					diagonal1 ++;
				if(i+n ==2)
					diagonal2 ++;
			}
		}
	}
	if(diagonal2 == 3 || diagonal1 == 3){
		return true;
	}else{
		for(int i=0; i<3; i++){
			if(rowSum[i]==3 || columnSum[i] ==3){
				return true;
			}
		}
	}

	return false;
}

//the meaning of the return value {1:computerWin, 2:humanWin, -1:tie, 0:notOver}
int TicTacToe::isOver(){
	if(computerWin()){
		return 1;
	}else if(humanWin()){
		return 2;
	}else if(step == 9){
		return -1;
	}
	return 0;
}

char TicTacToe::next(){
	if((step%2 && computerOrder == 2) || (step%2 == 0 && computerOrder == 1)){
		return 'c';
	}
	return 'h';
}

int main(){
	TicTacToe game;
	game.printBoard();
	while(!game.isOver()){
		game.place_chess();
	}
	if(game.computerWin())
		cout << "I win!" << endl;
	else if(game.humanWin())
		cout << "Congratulations! You win!" << endl;
	else{
		cout << "It is a tie!" << endl;
	}
	return 0;
}
