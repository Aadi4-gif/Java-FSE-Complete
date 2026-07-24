public class Main {
    interface Document {
        void open();
    }

    static class WordDocument implements Document {
        public void open() { System.out.println("Opening Word Document (.docx)..."); }
    }

    static class PdfDocument implements Document {
        public void open() { System.out.println("Opening PDF Document (.pdf)..."); }
    }

    static class ExcelDocument implements Document {
        public void open() { System.out.println("Opening Excel Spreadsheet (.xlsx)..."); }
    }

    abstract static class DocumentFactory {
        public abstract Document createDocument();
    }

    static class WordDocumentFactory extends DocumentFactory {
        public Document createDocument() { return new WordDocument(); }
    }

    static class PdfDocumentFactory extends DocumentFactory {
        public Document createDocument() { return new PdfDocument(); }
    }

    static class ExcelDocumentFactory extends DocumentFactory {
        public Document createDocument() { return new ExcelDocument(); }
    }

    public static void main(String[] args) {
        System.out.println("=== Factory Method Execution ===");

        DocumentFactory factory;
        Document doc;

        // Test Word
        factory = new WordDocumentFactory();
        doc = factory.createDocument();
        doc.open();

        // Test PDF
        factory = new PdfDocumentFactory();
        doc = factory.createDocument();
        doc.open();

        // Test Excel
        factory = new ExcelDocumentFactory();
        doc = factory.createDocument();
        doc.open();
    }
}

