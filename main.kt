/**
 * Funkcja wykonująca ruch robota na podstawie podanej przez użytkownika komendy
 * Zabezpieczenia:
 * - Przed wyjechaniem za granicę mapy
 * - Przed wjechaniem w przeszkodę
 */
fun move(znak : Char, gRobotPos : Array<Int>, gMapPos : Array<Array<Char>>) : Array<Int>
{
  // Wektor przesunięcia w zależności od podanej przez użytkownika komendy
  var vecMove = when(znak)
  { 
    'U' -> arrayOf<Int>(-1, 0)
    'D' -> arrayOf<Int>(1, 0)
    'L' -> arrayOf<Int>(0, -1)
    'R' -> arrayOf<Int>(0, 1)
    else -> arrayOf<Int>(0, 0)
  }
  
  // Zabezpieczenie: przed wjechaniem w przeszkodę
  if(gRobotPos[0] + vecMove[0] < 10 && gRobotPos[1] + vecMove[1] < 10)
  {
    if(gMapPos[gRobotPos[0] + vecMove[0]][gRobotPos[1] + vecMove[1]] != '1')
    {
      gRobotPos[0] += vecMove[0]
      gRobotPos[1] += vecMove[1]
    }
  }

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

/**
 * Funkcja wyświetlająca aktualną mapę oraz legendę
 */
fun printMap(gRobotPos : Array<Int>, gMapPos : Array<Array<Char>>)
{
  for(x in 0..9)
  {
    if(x == 0)
    {
      print("    ")
      for(i in 0..9)
      {
        print(i)
        print(" ")
      }
      println()
      print("    ")
      for(i in 0..18)
      {
        print("-")
      }
      println()
    }
    for(y in 0..9)
    {
      if(y == 0)
      {
        print(x)
        print(" | ")
      }
      if(x == gRobotPos[0] && y == gRobotPos[1])
      {
        print("R ")
      }
      else
      {
        print(gMapPos[x][y] + " ")
      }
    }
    println()
  }
  println()
  println("Legenda:")
  println("--------------------------")
  println(" -> R - pozycja robota")
  println(" -> ! - pozycja końcowa")
  println(" -> 0 - pole nieodwiedzone")
  println(" -> 1 - przeszkoda")
  println(" -> a-z - pole odwiedzone")
  println("---------------------------")
  println()
}

fun main()
{
  // Tworzenie mapy 10 x 10
  var mapPosition = Array(10) { Array<Char>(10) { '0' } }
  
  //Dodanie kilku przeszkód
  val obstacles = 3
  for(i in 0..obstacles)
  {
    mapPosition[(0..9).random()][(0..9).random()] = '1'
  }
  
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
    // Wyświetla aktualną pozycję robota, pozycję końcową oraz przeszkody
    printMap(robotPosition, mapPosition)

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
      robotPosition = move(userInput[i], robotPosition, mapPosition)
      if(mapPosition[robotPosition[0]][robotPosition[1]] == '0')
        mapPosition[robotPosition[0]][robotPosition[1]] = 'a'
      else
        mapPosition[robotPosition[0]][robotPosition[1]]++
    }
  }
  while(!(robotPosition[0] == endPosition[0] && robotPosition[1] == endPosition[1]))

  println("Gratulacje! Doszedłeś do mety i wygrałeś grę")
}
