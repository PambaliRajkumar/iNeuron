import java.util.Random;
import java.util.Scanner;

class Guesser
{
	int guessedNum;

	public int guessNumber(int i)
	{

		System.out.print("Hey Guesser-" + i + ", Kindly Guess the number : ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		guessedNum = sc.nextInt();
		return guessedNum;
	}
}

class Analyzer
{
	int resultRef;
	int attempts;
	int randomNum;
	int numOfGuessers;
	int boundary;
	int modeOfGame;
	int[] numFromGuesser;

	public void repeatition()
	{
		switch (boundary)
		{
		case 100:
			modeOfGame = 1;
			break;
		case 500:
			modeOfGame = 2;
			break;
		case 1000:
			modeOfGame = 3;
			break;
		}

		for (int i = 1; i <= attempts; i++)
		{
			getNumbersFromGuessers();
			analyzeResult();
		}
		if (resultRef == 0 || numOfGuessers != 1 && resultRef != 1)
		{
			System.out.println("Attempts Expired! ");
			System.out.println("Mystery Number is " + randomNum + ".");
		} else if (numOfGuessers != 1 && resultRef == 1)
		{
			System.out.println("Attempts Expired!");
			System.out.println("Mystery Number is " + randomNum + ".");
		}

	}

	public void selectLevelOfGame()
	{
		do
		{
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.print(
				"Please select Level to proceed Further : \nPress 1 to select Easy.\nPress 2 to select Moderate.\nPress 3 to select Hard.");
			modeOfGame = scan.nextInt();
			switch (modeOfGame)
			{
			case 1:attempts=5;
				boundary = 100;
				break;
			case 2:attempts=8;
				boundary = 500;
				break;
			case 3:attempts=10;
				boundary = 1000;
				break;
			default:
				System.out.println("PLEASE ENTER VALID INPUT!!");
			}
		} while (modeOfGame != 1 && modeOfGame != 2 && modeOfGame != 3);

	}

	public void selectNumOfGuessers()
	{
		System.out.print("Enter No.of Guessers : ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		numOfGuessers = sc.nextInt();
		numFromGuesser = new int[numOfGuessers];
	}

	public void getRandomNumber()
	{
		Random rand = new Random();
		randomNum = 1 + rand.nextInt(boundary);
		//System.out.println("Debug perspective " + randomNum);
		if (modeOfGame == 1)
			System.out.printf("Easy level:\nEach Guesser will have %d attempts.%nRange of numbers is from 1 to %d.%n",
					attempts,boundary);
		else if (modeOfGame == 2)
			System.out
				.printf("Moderate level:Each Guesser will have %d attempts.%nRange of numbers is from 1 to %d.%n", attempts,boundary);
		else if (modeOfGame == 3)
			System.out.printf("Hard level:Each Guesser will have %d attempts.%nRange of numbers is from 1 to %d.%n",
					attempts,boundary);
	}

	public void getNumbersFromGuessers()
	{
		Guesser[] g = new Guesser[numOfGuessers];
		for (int i = 0; i < g.length; i++)
		{
			g[i] = new Guesser();
			if (numFromGuesser[i] != -1)
			{
				numFromGuesser[i] = g[i].guessNumber(i);
				while (numFromGuesser[i] < 1 || numFromGuesser[i] > boundary)
				{
					System.out.printf("PLEASE ENTER NUMBER FROM %d to %d!! %n", 1, boundary);
					numFromGuesser[i] = g[i].guessNumber(i);
				}
			}
		}

	}

	public void analyzeResult()
	{

		for (int i = 0; i < numFromGuesser.length; i++)
		{
			if (randomNum == numFromGuesser[i])
			{
				System.out.print("Guesser-" + i + " ");
				numFromGuesser[i] = -1;
				resultRef++;
				if (resultRef == 1)
					System.out.println("won the Game!");
				else if (resultRef == 2)
					System.out.println("is Runner-up of the Game!");
			}

			else if (numFromGuesser[i] < randomNum && numFromGuesser[i] != -1)
				System.out.println("Hey Guesser-" + i + ", Mystery number is greater than entered number.");
			else if (numFromGuesser[i] > randomNum && numFromGuesser[i] != -1)
				System.out.println("Hey Guesser-" + i + ", Mystery number is lower than entered number.");
			if (resultRef == 2)
			{
				System.out.println("Game Over!");
				System.out.println("Mystery Number is " + randomNum + ".");
				System.exit(0);
			}
		}
	}
}

public class GuessingGame
{
	public static void main(String[] args)
	{
		Analyzer a = new Analyzer();
		a.selectNumOfGuessers();
		a.selectLevelOfGame();
		a.getRandomNumber();
		a.repeatition();

	}
}
