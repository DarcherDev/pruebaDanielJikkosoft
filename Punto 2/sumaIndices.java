public class Principal {

    public static void main(String[] args) {
        int[] arr = {9,2,4,6,5,7,8};
        try {
            int[] res = Principal.sumaIndices(arr,9);
            System.out.println(res[0]+" "+res[1]);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static int[] sumaIndices(int arreglo[], int num) throws Exception {
        int[] res = new int[2];
        if(arreglo.length < 2){
            throw new Exception("el arreglo como minimo debe ser de 2 posiciones");
        }
        for (int i = 0; i < arreglo.length; i++) {
            for (int j = i + 1; j < arreglo.length; j++) {
                if (arreglo[i] + arreglo[j] == num) {
                    res[0] = i;
                    res[1] = j;
                    return res; 
                }
            }
        }
        throw new Exception("No se encontraron dos números que sumen el valor objetivo.");
    }
}
