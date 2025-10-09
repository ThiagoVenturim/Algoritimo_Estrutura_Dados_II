import matplotlib.pyplot as plt

# Tamanhos dos arrays
sizes = [100, 250, 500, 1000, 5000, 10000]

# Tempos em nanosegundos
# Pré-ordenado
pre_sorted = {
    "quickSorFi": [99000, 630300, 437400, 1542600, 51044900, 56429100],
    "quickSorFiIn": [176000, 120600, 362200, 1424700, 27555600, 82087700],
    "quickSortMediana": [51900, 78700, 66900, 115200, 234700, 3972200],
    "quickSortRandom": [786100, 185600, 217600, 112100, 418900, 7095000]
}

# Aleatório
random_array = {
    "quickSorFi": [255300, 407700, 311500, 176400, 579700, 1881800],
    "quickSorFiIn": [180900, 92200, 71600, 230100, 758600, 1849700],
    "quickSortMediana": [47600, 191300, 291800, 1213200, 4008200, 8220300],
    "quickSortRandom": [1343700, 136300, 314700, 709700, 4478400, 15896300]
}

# Quase ordenado
almost_sorted = {
    "quickSorFi": [40300, 38000, 84900, 210000, 1010500, 2328600],
    "quickSorFiIn": [30600, 21400, 60600, 367700, 820600, 2674200],
    "quickSortMediana": [26300, 66500, 137500, 433600, 1680500, 6641400],
    "quickSortRandom": [28500, 89200, 156600, 482000, 2255300, 5194900]
}

def plot_times(times_dict, title, filename):
    plt.figure(figsize=(10,6))
    for key, values in times_dict.items():
        plt.plot(sizes, values, marker='o', label=key)
    plt.title(title)
    plt.xlabel("Tamanho do array")
    plt.ylabel("Tempo de execução (ns)")
    plt.xscale('log')
    plt.yscale('log')
    plt.grid(True, which="both", linestyle="--", linewidth=0.5)
    plt.legend()
    plt.savefig(filename, dpi=300)  # salva como PNG
    plt.close()  # fecha a figura para não sobrepor gráficos

# Criando os arquivos PNG
plot_times(pre_sorted, "QuickSort - Pré-ordenado", "quicksort_preordenado.png")
plot_times(random_array, "QuickSort - Aleatório", "quicksort_aleatorio.png")
plot_times(almost_sorted, "QuickSort - Quase ordenado", "quicksort_quaseordenado.png")

