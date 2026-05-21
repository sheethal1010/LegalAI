package com.legalai.app.network;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MockAiService {

    private static final Map<String, String[]> RESPONSES = new HashMap<>();
    private static final Random random = new Random();

    static {
        RESPONSES.put("criminal", new String[]{
                "In criminal matters under the IPC (Indian Penal Code), you have the right to legal representation from the moment of arrest. Key steps:\n\n1. Do not make any statements without your lawyer present\n2. Request a copy of the FIR (First Information Report) — this is your legal right under Section 207 CrPC\n3. Apply for bail if you are in custody. Bail can be granted by a Magistrate or Sessions Court\n4. Your lawyer can file a bail application citing Sections 436-439 CrPC\n\nWould you like more specific guidance on your situation?",
                "Under Section 154 CrPC, filing an FIR is your right. If police refuse to register an FIR, you can:\n1. Approach a superior police officer\n2. Send a written complaint to the Superintendent of Police\n3. File a complaint directly before a Magistrate under Section 156(3) CrPC\n\nAlways keep a copy of your complaint for records."
        });

        RESPONSES.put("family", new String[]{
                "Under the Hindu Marriage Act 1955 or the Special Marriage Act 1954, divorce can be sought on grounds including:\n• Cruelty (physical or mental)\n• Desertion for 2+ years\n• Adultery\n• Conversion to another religion\n• Mental disorder\n\nMutual consent divorce under Section 13B requires 6 months to 18 months. I recommend consulting a family law advocate to understand which grounds apply to your case.",
                "Child custody in India is governed by the Guardians and Wards Act 1890 and personal laws. Courts prioritize the welfare of the child above all else. Factors considered include:\n• Age of the child (children below 5 usually with mother)\n• Financial stability of each parent\n• Child's preference (if old enough)\n• School and stability considerations\n\nInterim custody orders can be obtained quickly if there is urgency."
        });

        RESPONSES.put("property", new String[]{
                "Under the Transfer of Property Act 1882 and Registration Act 1908, property transactions require:\n1. Sale deed drafted by an advocate\n2. Payment of stamp duty (varies by state, typically 4-7%)\n3. Registration at the Sub-Registrar's office within 4 months\n4. Mutation of property records in local municipal records\n\nAlways verify title documents going back at least 30 years and check for encumbrances at the Sub-Registrar's office.",
                "If you are facing property disputes, you can:\n1. File a civil suit for declaration of title\n2. Apply for an injunction to prevent sale or construction\n3. File a complaint if there is criminal fraud involved\n\nProperty disputes can also be resolved through mediation, which is faster and less expensive than court proceedings."
        });

        RESPONSES.put("corporate", new String[]{
                "Under the Companies Act 2013, a Private Limited Company provides limited liability protection. Key requirements:\n• Minimum 2 directors and 2 shareholders\n• Minimum paid-up capital: No minimum (removed in 2015)\n• Register with MCA (Ministry of Corporate Affairs)\n• Obtain DIN (Director Identification Number) and DSC\n\nThe process typically takes 15-20 working days. Alternatively, an LLP (Limited Liability Partnership) under the LLP Act 2008 offers similar protection with less compliance.",
                "A contract is legally enforceable under the Indian Contract Act 1872 when it has:\n1. Offer and Acceptance\n2. Lawful Consideration\n3. Free Consent (no coercion, fraud, or misrepresentation)\n4. Lawful Object\n5. Capacity of parties\n\nFor breach of contract, you can claim damages, specific performance, or injunction depending on the nature of breach."
        });

        RESPONSES.put("consumer", new String[]{
                "Under the Consumer Protection Act 2019, you can file a complaint for:\n• Defective goods or deficient services\n• Unfair trade practices\n• Misleading advertisements\n\nJurisdiction:\n• District Commission: Claims up to ₹1 crore\n• State Commission: ₹1 crore to ₹10 crore\n• National Commission: Above ₹10 crore\n\nYou can also file online at consumerhelpline.gov.in. No lawyer is required for filing — you can represent yourself.",
                "For e-commerce disputes, the Consumer Protection (E-Commerce) Rules 2020 require platforms to resolve complaints within specific timelines. If unresolved, file at the National Consumer Helpline (1800-11-4000) or the INGRAM portal."
        });

        RESPONSES.put("labour", new String[]{
                "Under the Industrial Disputes Act 1947 and the Code on Industrial Relations 2020, employees have strong protections:\n• Wrongful termination can be challenged before the Labour Court\n• Notice period requirements apply to both parties\n• Gratuity is payable after 5 years of continuous service (Payment of Gratuity Act 1972)\n• PF (Provident Fund) contributions are mandatory under EPF Act 1952\n\nFor unpaid wages, file a complaint under the Payment of Wages Act before the appropriate authority.",
                "Sexual harassment at workplace is governed by the POSH Act (Prevention, Prohibition and Redressal) 2013. You should:\n1. File a complaint with the Internal Complaints Committee (ICC) within 3 months\n2. If no ICC exists, approach the Local Complaints Committee\n3. Criminal complaint under IPC Section 354A is also available\n\nAll employers with 10+ employees must have an ICC."
        });

        RESPONSES.put("ip", new String[]{
                "Intellectual Property protection in India:\n\n• Trademark: Register under Trade Marks Act 1999. Protection for 10 years, renewable. File at IP India portal.\n• Copyright: Automatic protection on creation under Copyright Act 1957. Registration gives evidentiary value.\n• Patent: File under Patents Act 1970. Valid for 20 years. Must be novel, non-obvious, and industrially applicable.\n• Design: Register under Designs Act 2000. Protection for 10+5 years.\n\nIP India website: ipindia.gov.in",
        });

        RESPONSES.put("constitutional", new String[]{
                "Fundamental Rights under Part III of the Constitution of India (Articles 12-35) include:\n• Right to Equality (Art 14-18)\n• Right to Freedom (Art 19-22) including freedom of speech, movement, occupation\n• Right against Exploitation (Art 23-24)\n• Right to Freedom of Religion (Art 25-28)\n• Cultural and Educational Rights (Art 29-30)\n• Right to Constitutional Remedies (Art 32) — Dr. Ambedkar called this the 'heart and soul' of the Constitution\n\nFor violation of fundamental rights, you can approach the High Court (Art 226) or Supreme Court (Art 32) directly.",
        });

        RESPONSES.put("default", new String[]{
                "Thank you for your query. I'm Legal.AI, specializing in Indian law. Could you provide more details about your situation so I can give you more specific guidance?\n\nI can help with:\n⚖️ Criminal law and FIR matters\n👨‍👩‍👧 Family law and divorce\n🏠 Property transactions and disputes\n🏢 Corporate law and contracts\n🛒 Consumer protection\n👔 Labour and employment law\n💡 Intellectual property\n📜 Constitutional rights\n\nWhat area concerns you most?",
                "I understand you have a legal concern. To provide the most accurate guidance under Indian law, could you:\n1. Specify the type of legal issue (criminal, civil, family, property, etc.)\n2. Mention the state/jurisdiction if relevant\n3. Share the key facts of your situation\n\nRest assured, everything you share is confidential. I'm here to help."
        });
    }

    public static String getResponse(String category, String userMessage) {
        String key = mapCategory(category);
        String[] responses = RESPONSES.get(key);
        if (responses == null) responses = RESPONSES.get("default");
        return responses[random.nextInt(responses.length)];
    }

    private static String mapCategory(String category) {
        if (category == null) return "default";
        category = category.toLowerCase();
        if (category.contains("criminal") || category.contains("fir") || category.contains("bail")) return "criminal";
        if (category.contains("family") || category.contains("divorce") || category.contains("custody")) return "family";
        if (category.contains("property") || category.contains("real estate") || category.contains("land")) return "property";
        if (category.contains("corporate") || category.contains("business") || category.contains("contract")) return "corporate";
        if (category.contains("consumer")) return "consumer";
        if (category.contains("labour") || category.contains("labor") || category.contains("employment") || category.contains("job")) return "labour";
        if (category.contains("ip") || category.contains("intellectual") || category.contains("trademark") || category.contains("patent")) return "ip";
        if (category.contains("constitutional") || category.contains("civil rights") || category.contains("fundamental")) return "constitutional";
        return "default";
    }
}
