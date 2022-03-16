/**
 * Funkcja wykonująca ruch robota na podstawie podanej przez użytkownika komendy
 * Zabezpieczenie: przed wyjechaniem za granicę mapy
 */
fun move(znak : Char, gRobotPos : Array<Int>) : Array<Int>
{
    // Tymczasowa zmienna pozycji robota
    var robotPosition = gRobotPos
    
    // Wektor przesunięcia w zależności od podanej przez użytkownika komendy
    var vecMove = when(znak)
    { 
        'U' -> arrayOf<Int>(0, 1)
        'D' -> arrayOf<Int>(0, -1)
        'L' -> arrayOf<Int>(-1, 0)
        'R' -> arrayOf<Int>(1, 0)
        else -> arrayOf<Int>(0, 0)
    }
    robotPosition[0] += vecMove[0]
    robotPosition[1] += vecMove[1]

    // Zabezpieczenie: przed wyjechaniem za granicę mapy
    if(robotPosition[0] < 0)
    {
        robotPosition[0] = 0
    }
    else if(robotPosition[0] > 9)
    {
        robotPosition[0] = 9
    }
    if(robotPosition[1] < 0)
    {
        robotPosition[1] = 0
    }
    else if(robotPosition[1] > 9)
    {
        robotPosition[1] = 9
    }
    return robotPosition
}

fun main()
{
    // Tworzenie mapy 10 x 10
    var mapPosition = Array(10) { Array<Int>(10) { 0 } }
    
    // Generowanie pozycji startowej
    var startPosition = Array<Int>(2) { (0..9).random() }
    
    // Generowanie pozycji końcowej
    var endPosition = Array<Int>(2) { (0..9).random() }
    if(9 - startPosition[0] > 5)
    {
        endPosition[0] = 9
    }
    else endPosition[0] = 0
    
    if(9 - startPosition[1] > 5)
    {
        endPosition[1] = 9
    }
    else endPosition[1] = 0

    // Określenie pozycji robota na pozycji startowej
    var robotPosition = startPosition
    
    print("endPosition: (")
    for(x in endPosition)
    {
        print(x)
        print(",")
    }
    print(")")
    println("")
    do
    {
        print("robotPosition: (")
        for(x in robotPosition)
        {
            print(x)
            print(",")
        }
        print(")")
        println("")

        // Wczytanie tekstu od użytkownika
        var tempUserInput = readLine()

        // Zabezpieczenie: tylko pięć znaków może zostać wczytane jednorazowo
        var userInput : String = "$tempUserInput"//.take(5)
        if(userInput.length > 5)
            userInput = "$userInput".take(5)
            
        for(i in 0..userInput.length-1)
            robotPosition = move(userInput[i], robotPosition)
    }
    while(!(robotPosition[0] == endPosition[0] && robotPosition[1] == endPosition[1]))
    
    println("Koniec gry")
}
