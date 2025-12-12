package pt.iade.ei.cobuy.ui.screens.recipe

import pt.iade.ei.cobuy.R

data class RecipeUiExtras(
    val imageRes: Int,
    val preparationSteps: List<String>
)


fun recipeExtras(recipeId: Int): RecipeUiExtras {
    return when (recipeId) {

        1 -> RecipeUiExtras(
            imageRes = R.drawable.massa_carbonara,
            preparationSteps = listOf(
                "Coza o esparguete em água a ferver com sal até ficar al dente.",
                "Misture os ovos, as natas, o parmesão, o sal (pouco) e a pimenta, e reserve.",
                "Numa frigideira, aqueça azeite, salteie o bacon e junte o alho por alguns segundos.",
                "Adicione o esparguete cozido e envolva bem.",
                "Fora do lume, junte o molho de ovos e mexa até ficar cremoso.",
                "Finalize com pimenta e parmesão."
            )
        )

        2 -> RecipeUiExtras(
            imageRes = R.drawable.frangogrelhado,
            preparationSteps = listOf(
                "Tempere o frango com sal, pimenta e azeite e deixe repousar.",
                "Grelhe o frango de ambos os lados até dourar.",
                "Refogue cebola e alho, junte o arroz, mexa e adicione água e sal. Coza até ficar solto.",
                "Salteie a cenoura e o pimento em azeite até ficarem macios.",
                "Sirva o arroz com o frango fatiado e os legumes por cima."
            )
        )

        3 -> RecipeUiExtras(
            imageRes = R.drawable.lasanha,
            preparationSteps = listOf(
                "Refogue cebola e alho em azeite, junte a carne picada e tempere.",
                "Adicione o molho de tomate e deixe cozinhar alguns minutos.",
                "Prepare o molho bechamel com leite, manteiga e farinha.",
                "Num tabuleiro, faça camadas alternadas de lasanha, carne, bechamel e queijo.",
                "Leve ao forno a 180 °C durante 25–30 minutos."
            )
        )

        4 -> RecipeUiExtras(
            imageRes = R.drawable.arroz_de_marsico,
            preparationSteps = listOf(
                "Refogue cebola e alho em azeite até dourar.",
                "Adicione o tomate e o pimento e deixe cozinhar alguns minutos.",
                "Junte o arroz e envolva no refogado.",
                "Acrescente o caldo e o vinho branco, tempere e deixe cozer.",
                "Adicione o marisco quando o arroz estiver quase pronto.",
                "Finalize com coentros e sirva com o arroz ainda cremoso."            )
        )

        5 -> RecipeUiExtras(
            imageRes = R.drawable.bacalhauabras,
            preparationSteps = listOf(
                "Coza o bacalhau, escorra e desfie-o.",
                "Refogue cebola e alho em azeite, junte o bacalhau e envolva.",
                "Adicione a batata palha e misture bem.",
                "Junte os ovos batidos e mexa até ficarem cremosos.",
                "Tempere, adicione salsa e finalize com azeitonas."            )
        )

        6 -> RecipeUiExtras(
            imageRes = R.drawable.salmaonoforno,
            preparationSteps = listOf(
                "Tempere o salmão com sal, pimenta, alho e azeite.",
                "Corte as batatas em rodelas finas e disponha num tabuleiro, temperando com sal, pimenta e azeite.",
                "Coloque o salmão por cima das batatas e regue com sumo de limão.",
                "Adicione a cebola às rodelas e ervas aromáticas.",
                "Leve ao forno a 180 ºC por 25–30 minutos, até o salmão estar cozido.",
                "Finalize com salsa fresca ou limão."            )
        )

        7 -> RecipeUiExtras(
            imageRes = R.drawable.salamedechocolate,
            preparationSteps = listOf(
                "Parta as bolachas grosseiramente, deixando pedaços irregulares.",
                "Derreta a manteiga numa tigela.",
                "Adicione o açúcar e o chocolate em pó e misture bem.",
                "Junte o ovo e mexa rapidamente.",
                "Acrescente as bolachas partidas e envolva tudo.",
                "Coloque a mistura sobre papel vegetal e molde em forma de rolo.",
                "Enrole bem, aperte as pontas e leve ao frigorífico 3–4 horas.",
                "Depois de firme, corte em fatias."            )
        )

        8 -> RecipeUiExtras(
            imageRes = R.drawable.chilicomcarne,
            preparationSteps = listOf(
                "Aqueça um fio de azeite numa panela e refogue a cebola e o alho até dourar.",
                "Adicione a carne picada e deixe cozinhar até ficar solta e ligeiramente dourada.",
                "Junte o pimento vermelho em cubos e deixe cozinhar alguns minutos.",
                "Acrescente o feijão vermelho, o molho de tomate e a polpa de tomate.",
                "Tempere com sal, pimenta preta, cominhos, paprika e malagueta.",
                "Deixe cozinhar em lume brando cerca de 20–30 minutos até apurar."            )
        )

        9 -> RecipeUiExtras(
            imageRes = R.drawable.panquecas,
            preparationSteps = listOf(
                "Numa tigela, misture a farinha, o fermento e o açúcar.",
                "Adicione os ovos e o leite, mexendo até obter uma massa lisa.",
                "Junte a manteiga derretida e envolva tudo.",
                "Aqueça uma frigideira levemente untada.",
                "Deite pequenas porções de massa e deixe cozinhar até aparecerem bolhas.",
                "Vire a panqueca e cozinhe mais alguns segundos.",
                "Sirva com mel, fruta ou chocolate."            )
        )

        10 -> RecipeUiExtras(
            imageRes = R.drawable.omelete,
            preparationSteps = listOf(
                "Bata os ovos com uma pitada de sal e pimenta.",
                "Junte o fiambre em pedaços e o queijo ralado, mexendo ligeiramente.",
                "Aqueça uma frigideira antiaderente e derreta a manteiga.",
                "Deite a mistura na frigideira e deixe cozinhar em lume médio-baixo.",
                "Quando estiver quase firme, dobre a omelete.",
                "Deixe terminar de cozinhar até o interior ficar cremoso."            )
        )

        11 -> RecipeUiExtras(
            imageRes = R.drawable.sopadelegumes,
            preparationSteps = listOf(
                "Corte a cenoura, batata, courgette e cebola em pedaços pequenos.",
                "Coloque todos os legumes numa panela com a água, azeite, sal e pimenta.",
                "Cozinhe até os legumes ficarem bem macios.",
                "Triture a sopa até ficar cremosa.",
                "Ajuste o sal e sirva quente."            )
        )

        12 -> RecipeUiExtras(
            imageRes = R.drawable.tostamista,
            preparationSteps = listOf(
                "Barre ligeiramente o exterior das fatias de pão com manteiga.",
                "No interior, coloque as fatias de queijo e fiambre.",
                "Feche a tosta e coloque numa sanduicheira ou frigideira.",
                "Deixe dourar de ambos os lados até o queijo derreter.",
                "Sirva quente."            )
        )

        13 -> RecipeUiExtras(
            imageRes = R.drawable.wrapdefrango,
            preparationSteps = listOf(
                "Tempere o peito de frango com sal e pimenta e grelhe até dourar.",
                "Corte o frango em tiras finas.",
                "Espalhe a maionese no centro das tortilhas.",
                "Adicione as folhas de alface, o tomate fatiado e o frango.",
                "Enrole o wrap apertando bem as pontas.",
                "Pode cortar ao meio para servir mais facilmente."            )
        )

        14 -> RecipeUiExtras(
            imageRes = R.drawable.haumburguer,
            preparationSteps = listOf(
                "Tempere a carne picada com sal e pimenta e molde dois hambúrgueres.",
                "Grelhe ou frite os hambúrgueres até ficarem dourados de ambos os lados.",
                "Coloque o queijo por cima para derreter ligeiramente.",
                "Torre levemente o pão de hambúrguer.",
                "Monte: pão → alface → tomate → hambúrguer → molhos → pão.",
                "Sirva ainda quente."            )
        )

        15 -> RecipeUiExtras(
            imageRes = R.drawable.pizza,
            preparationSteps = listOf(
                "Numa taça misture a farinha de trigo, o sal e o fermento de padeiro seco.",
                "Adicione a água morna e o azeite aos poucos, mexendo até formar uma massa homogénea.",
                "Amasse durante alguns minutos e deixe levedar cerca de 30–40 minutos.",
                "Estenda a massa numa forma de pizza ou tabuleiro.",
                "Espalhe o molho de tomate por cima da massa.",
                "Cubra com o queijo mozzarella ralado, o tomate em rodelas e polvilhe com orégãos secos.",
                "Leve ao forno pré-aquecido a 200 ºC até a massa ficar dourada e o queijo derretido."            )
        )

        16 -> RecipeUiExtras(
            imageRes = R.drawable.bolonhesa,
            preparationSteps = listOf(
                "Coza o esparguete em água a ferver com sal até ficar al dente.",
                "Num tacho, refogue a cebola picada e o alho em azeite.",
                "Adicione a carne picada e cozinhe até ganhar cor.",
                "Junte o molho de tomate, a polpa, sal e pimenta.",
                "Deixe cozinhar em lume brando durante 10–15 minutos.",
                "Envolva o esparguete com o molho e sirva quente."            )
        )

        17 -> RecipeUiExtras(
            imageRes = R.drawable.arrozdoce,
            preparationSteps = listOf(
                "Aqueça o leite com o pau de canela e uma pitada de sal.",
                "Junte o arroz e cozinhe em lume brando, mexendo sempre.",
                "Quando o arroz estiver quase cozido, adicione o açúcar e misture bem.",
                "Retire um pouco do leite quente, misture com as gemas e incorpore lentamente no tacho.",
                "Cozinhe mais 2–3 minutos até engrossar ligeiramente.",
                "Sirva ainda quente ou deixe arrefecer."            )
        )

        18 -> RecipeUiExtras(
            imageRes = R.drawable.gelatinacomiogurte,
            preparationSteps = listOf(
                "Dissolva a gelatina em pó na água quente e mexa até ficar homogénea.",
                "Deixe arrefecer ligeiramente, mas sem solidificar.",
                "Misture a gelatina morna com o iogurte natural até obter um creme uniforme.",
                "Distribua por taças e leve ao frigorífico durante 2 a 3 horas.",
                "Decore com morangos antes de servir (opcional)."            )
        )

        19 -> RecipeUiExtras(
            imageRes = R.drawable.saladamediterranea,
            preparationSteps = listOf(
                "Corte o tomate e o pepino em pedaços médios.",
                "Misture numa taça a alface, tomate, pepino e azeitonas.",
                "Adicione o queijo feta em cubos.",
                "Regue com azeite e vinagre.",
                "Polvilhe com orégãos e envolva suavemente.",
                "Sirva fresca."            )
        )

        20 -> RecipeUiExtras(
            imageRes = R.drawable.bolodechocolate,
            preparationSteps = listOf(
                "Misture a farinha, o cacau em pó, o açúcar e o fermento numa tigela.",
                "Adicione os ovos, o leite e a manteiga derretida.",
                "Bata até obter uma massa homogénea.",
                "Verta para uma forma untada.",
                "Leve ao forno a 180 °C por 30–35 minutos.",
                "Deixe arrefecer antes de servir."            )
        )

        else -> RecipeUiExtras(
            imageRes = R.drawable.placeholder,
            preparationSteps = listOf()
        )
    }
}
