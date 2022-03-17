/**
 * Funkcja wykonująca ruch robota na podstawie podanej przez użytkownika komendy
 * Zabezpieczenie: przed wyjechaniem za granicę mapy
 */
fun move(znak : Char, gRobotPos : Array<Int>) : Array<Int>
{
  // Wektor przesunięcia w zależności od podanej przez użytkownika komendy
  var vecMove = when(znak)
  { 
    'U' -> arrayOf<Int>(0, 1)
    'D' -> arrayOf<Int>(0, -1)
    'L' -> arrayOf<Int>(-1, 0)
    'R' -> arrayOf<Int>(1, 0)
    else -> arrayOf<Int>(0, 0)
  }
  gRobotPos[0] += vecMove[0]
  gRobotPos[1] += vecMove[1]

  // Zabezpieczenie: przed wyjechaniem za granicę mapy
  if(gRobotPos[0] < 0)
    gRobotPos[0] = 0
  else if(gRobotPos[0] > 9)
    gRobotPos[0] = 9

  if(gRobotPos[1] < 0)
    gRobotPos[1] = 0
  else if(gRobotPos[1] > 9)
    gRobotPos[1] = 9

  return gRobotPos
}

fun main()
{
  // Tworzenie mapy 10 x 10
  var mapPosition = Array(10) { Array<Char>(10) { '0' } }
  // Generowanie pozycji startowej
  var startPosition = Array<Int>(2) { (0..9).random() }

  // Generowanie pozycji końcowej
  var endPosition = Array<Int>(2) { (0..9).random() }
  if(9 - startPosition[0] > 5)
    endPosition[0] = 9
  else
    endPosition[0] = 0

  if(9 - startPosition[1] > 5)
    endPosition[1] = 9
  else
    endPosition[1] = 0
  mapPosition[endPosition[0]][endPosition[1]] = '!'
  // Określenie pozycji robota na pozycji startowej
  var robotPosition = startPosition

  do
  {
    // Wyświetla aktualną pozycję robota oraz pozycję końcową
    for (array in mapPosition) {
      for (value in array)
        print(value + " ")
      println()
    }

    println("------------------------------")
    println("Pozycja robota: (" + robotPosition[0] + ", " + robotPosition[1] + ")")
    println("Pozycja końcowa: (" + endPosition[0] + ", " + endPosition[1] + ")")
    println("------------------------------")

    // Wczytanie tekstu od użytkownika
    print("Wprowadź komendę: ")
    var tempUserInput = readLine()
    println("")

    // Zabezpieczenie: tylko pięć znaków może zostać wczytane jednorazowo
    var userInput : String = "$tempUserInput"
    if(userInput.length > 5)
      userInput = "$userInput".take(5)

    for(i in 0..userInput.length-1)
    {
      robotPosition = move(userInput[i], robotPosition)
      if(mapPosition[robotPosition[0]][robotPosition[1]] == '0')
        mapPosition[robotPosition[0]][robotPosition[1]] = 'a'
      else
        mapPosition[robotPosition[0]][robotPosition[1]]++
    }
  }
  while(!(robotPosition[0] == endPosition[0] && robotPosition[1] == endPosition[1]))

  println("Koniec gry")
}
