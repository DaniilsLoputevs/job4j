package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

public class StoreXML {
    private File target;

    /**
     * @param target - Файл куда будет сохраняться данные.
     */
    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * @param list - сохраняет данные из list в файл target.
     */
    public void save(List<Entry> list) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(
                    new Entries(list),
//                    System.out
                    target
            );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    @XmlRootElement
    public static class Entries {
        private List<Entry> values;

        public Entries() {
        }

        public Entries(List<Entry> values) {
            this.values = values;
        }

        public List<Entry> getValues() {
            return values;
        }

        @XmlElement(name = "entry")
        public void setValues(List<Entry> values) {
            this.values = values;
        }

    }

}