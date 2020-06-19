package ru.job4j.exam2;

public class ValidateGameParam {
    String waringMessage;

    /**
     * Validate Player params.
     * rsl = rsl && (conditions) >>
     * If check before fell, program won't start check this conditions, only check rsl == false.
     *
     * @param params - player params. (short form, else so long var name)
     * @return throw validate or not.
     */
    public boolean checkParams(int[] params, Field field) {
        var rsl = true;
        var fieldLength = field.getSize();
        if (params[0] >= fieldLength || params[1] >= fieldLength) {
            rsl = false;
            waringMessage = "Turn params is out of field range." + System.lineSeparator();
        }
        if (rsl && (!field.isCellFree(params[0], params[1]))) {
            rsl = false;
            waringMessage = "This cell isn't free." + System.lineSeparator();
        }
        return rsl;
    }

}
