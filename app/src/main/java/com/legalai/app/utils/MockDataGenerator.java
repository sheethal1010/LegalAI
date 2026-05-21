package com.legalai.app.utils;

import com.legalai.app.models.AiAgent;
import com.legalai.app.models.GlossaryTerm;
import com.legalai.app.models.Lawyer;
import com.legalai.app.models.LegalCase;
import com.legalai.app.models.NotificationItem;
import com.legalai.app.models.OnboardingPage;
import com.legalai.app.models.PracticeArea;

import java.util.ArrayList;
import java.util.List;

public class MockDataGenerator {

    public static List<Lawyer> getLawyers() {
        List<Lawyer> list = new ArrayList<>();
        list.add(new Lawyer("L001","Adv. Priya Sharma","Criminal Law",4.9,218,14,"₹3,000",
                "Senior criminal advocate with 14 years experience in Delhi High Court. Specializes in bail matters, trial advocacy, and white-collar crime defense. Former Additional Public Prosecutor.",
                "New Delhi",true,"PS","9876543210"));
        list.add(new Lawyer("L002","Adv. Rajesh Kumar","Family Law",4.7,142,11,"₹2,500",
                "Expert in matrimonial disputes, divorce proceedings, child custody, and domestic violence cases. Empathetic approach with a focus on mediation and amicable resolution.",
                "Mumbai",true,"RK","9123456780"));
        list.add(new Lawyer("L003","Adv. Ananya Iyer","Corporate Law",4.8,196,16,"₹5,000",
                "Corporate lawyer specializing in M&A, startup incorporation, shareholder agreements, and commercial contracts. Advised 50+ startups through funding rounds.",
                "Bengaluru",true,"AI","8877665544"));
        list.add(new Lawyer("L004","Adv. Mohammed Farooq","Property Law",4.6,89,9,"₹2,000",
                "Expert in property transactions, title verification, RERA disputes, and landlord-tenant matters across Karnataka and Andhra Pradesh.",
                "Hyderabad",false,"MF","9900112233"));
        list.add(new Lawyer("L005","Adv. Sunita Verma","Consumer Protection",4.8,174,12,"₹1,500",
                "Consumer rights champion with 200+ successful cases before District and State Consumer Commissions. Expert in e-commerce disputes and product liability.",
                "Pune",true,"SV","9001122334"));
        list.add(new Lawyer("L006","Adv. Karthik Nair","Labour Law",4.7,131,13,"₹2,000",
                "Employment and labour law specialist with expertise in wrongful termination, POSH Act compliance, and Industrial Disputes. Represents both employers and employees.",
                "Chennai",true,"KN","9988776655"));
        list.add(new Lawyer("L007","Adv. Deepika Joshi","Intellectual Property",4.9,203,17,"₹4,000",
                "IP specialist handling trademark registration, patent prosecution, copyright enforcement, and IP litigation across all IP Tribunals and High Courts in India.",
                "Mumbai",true,"DJ","9112233445"));
        list.add(new Lawyer("L008","Adv. Arjun Mehta","Constitutional Law",4.8,167,15,"₹3,500",
                "Constitutional law expert with extensive PIL experience. Successfully argued cases before the Supreme Court on fundamental rights, environmental law, and electoral matters.",
                "New Delhi",true,"AM","9443322110"));
        list.add(new Lawyer("L009","Adv. Rekha Pillai","Civil Law",4.5,78,8,"₹1,800",
                "Civil litigation expert handling property disputes, injunctions, suits for damages, and specific performance. Kerala High Court empanelled advocate.",
                "Kochi",true,"RP","9562233441"));
        list.add(new Lawyer("L010","Adv. Vikram Bose","Tax Law",4.7,112,10,"₹3,500",
                "Tax advocate specializing in income tax disputes, GST litigation, and tax planning for HNIs and corporates. Former IRS officer with 6 years service.",
                "Kolkata",false,"VB","9334455667"));
        return list;
    }

    public static List<AiAgent> getAgents() {
        List<AiAgent> list = new ArrayList<>();
        list.add(new AiAgent("A001","Contract Analyzer","Upload or paste any contract and I'll identify risky clauses, missing provisions, and recommend amendments. Covers NDA, employment, service, and sale agreements.","📄","Contract",4.9,"8.2k",
                "You are a contract analysis expert. Analyze contracts for risky clauses, missing standard provisions, one-sided terms, and legal compliance under Indian law. Be specific with section references."));
        list.add(new AiAgent("A002","FIR Assistant","I help you understand your rights when dealing with police, guide you on filing FIRs, and explain the criminal process step by step.","🚔","Criminal",4.8,"5.1k",
                "You are a criminal procedure expert. Help users understand FIR filing, bail applications, rights during arrest, and the criminal justice process under IPC and CrPC."));
        list.add(new AiAgent("A003","Divorce & Family Guide","Navigate divorce, child custody, maintenance, and family law matters with sensitivity and accuracy under Hindu, Muslim, and Special Marriage Act laws.","👨‍👩‍👧","Family",4.9,"6.3k",
                "You are a family law expert. Provide guidance on divorce, custody, alimony, maintenance, and matrimonial property under Hindu Marriage Act, Muslim Personal Law, and Special Marriage Act."));
        list.add(new AiAgent("A004","Property Advisor","Expert guidance on property purchases, sale deeds, RERA disputes, landlord-tenant issues, and property title verification.","🏠","Property",4.7,"4.7k",
                "You are a property law expert. Help with property transactions, title verification, RERA disputes, rental agreements, and property registration procedures across Indian states."));
        list.add(new AiAgent("A005","Consumer Rights Champion","Know your rights as a consumer. I help you draft complaints, understand refund rights, and navigate consumer forums.","🛒","Consumer",4.8,"3.9k",
                "You are a consumer protection expert. Help users file consumer complaints, understand their rights under Consumer Protection Act 2019, and navigate consumer forums at district, state, and national levels."));
        list.add(new AiAgent("A006","Startup Legal Guide","Everything a founder needs — company incorporation, shareholder agreements, term sheets, IP protection, and compliance.","🚀","Corporate",4.9,"7.4k",
                "You are a startup and corporate law expert. Guide founders on company incorporation, shareholder agreements, ESOP, funding rounds, term sheets, IP strategy, and regulatory compliance."));
        list.add(new AiAgent("A007","Labour Rights Advisor","Your guide to employment law — termination, PF, gratuity, POSH Act, and workplace rights.","👔","Labour",4.7,"2.8k",
                "You are a labour and employment law expert. Advise on employee rights, wrongful termination, PF/ESI, gratuity, POSH Act compliance, and labour court procedures."));
        list.add(new AiAgent("A008","IP Protector","Protect your brand, invention, and creative work. Expert guidance on trademarks, patents, copyrights, and design registration.","💡","IP",4.8,"3.1k",
                "You are an intellectual property law expert. Guide users on trademark, patent, copyright, and design registration in India. Help identify infringement and enforcement options."));
        return list;
    }

    public static List<PracticeArea> getPracticeAreas() {
        List<PracticeArea> list = new ArrayList<>();
        list.add(new PracticeArea("PA001","Criminal Law","⚖️",24,0xFF1A237E));
        list.add(new PracticeArea("PA002","Family Law","👨‍👩‍👧",18,0xFF4A148C));
        list.add(new PracticeArea("PA003","Property Law","🏠",21,0xFF1B5E20));
        list.add(new PracticeArea("PA004","Corporate Law","🏢",15,0xFF880E4F));
        list.add(new PracticeArea("PA005","Consumer Rights","🛒",12,0xFFE65100));
        list.add(new PracticeArea("PA006","Labour Law","👔",16,0xFF006064));
        list.add(new PracticeArea("PA007","Intellectual Property","💡",9,0xFF33691E));
        list.add(new PracticeArea("PA008","Constitutional Law","📜",11,0xFF37474F));
        return list;
    }

    public static List<GlossaryTerm> getGlossaryTerms() {
        List<GlossaryTerm> list = new ArrayList<>();
        list.add(new GlossaryTerm("Habeas Corpus","A writ requiring a person under arrest to be brought before a judge or court. It protects individuals against unlawful and indefinite imprisonment. Filed under Article 32 (Supreme Court) or Article 226 (High Court).","Constitutional"));
        list.add(new GlossaryTerm("FIR (First Information Report)","The first document recorded by police upon receiving information about a cognizable offence. Registered under Section 154 CrPC. You have the right to a free copy of the FIR.","Criminal"));
        list.add(new GlossaryTerm("Bail","Temporary release of an accused person awaiting trial, sometimes on condition that a sum of money is lodged to guarantee their appearance in court. Governed by Sections 436-439 CrPC.","Criminal"));
        list.add(new GlossaryTerm("Injunction","A court order requiring a party to do or refrain from doing a specific act. Interim injunctions are temporary; permanent injunctions are final orders.","Civil"));
        list.add(new GlossaryTerm("Affidavit","A written statement confirmed by oath or affirmation, used as evidence in court proceedings. Must be sworn before a Notary Public or Oath Commissioner.","General"));
        list.add(new GlossaryTerm("Caveat","A formal notice given by a party to a court asking that no order be made in a particular matter without first hearing from the caveator. Valid for 90 days.","Civil"));
        list.add(new GlossaryTerm("Anticipatory Bail","Bail granted by a Sessions Court or High Court to a person who apprehends arrest under Section 438 CrPC. Provides protection before actual arrest.","Criminal"));
        list.add(new GlossaryTerm("Power of Attorney","A legal document that gives one person the power to act for another person in legal, financial, or medical matters. Can be general or specific.","General"));
        list.add(new GlossaryTerm("Mandamus","A writ issued by a superior court commanding a lower court, tribunal, public authority or government to perform a public or statutory duty.","Constitutional"));
        list.add(new GlossaryTerm("Amicus Curiae","Latin for 'friend of the court.' A person or organization that is not a party to a case but assists the court by offering relevant information or expertise.","General"));
        list.add(new GlossaryTerm("Ex Parte","A legal proceeding conducted at the request of and for the benefit of one party only, without notice to or contest by the other party.","Civil"));
        list.add(new GlossaryTerm("Subpoena","A writ ordering a person to attend a court. Failure to comply can result in contempt of court proceedings.","General"));
        list.add(new GlossaryTerm("Certiorari","A writ by which a higher court reviews a decision of a lower court to determine whether the lower court acted within its jurisdiction.","Constitutional"));
        list.add(new GlossaryTerm("PIL (Public Interest Litigation)","Litigation filed for the protection of the public interest. Any citizen can file a PIL in the High Court or Supreme Court. No court fee required.","Constitutional"));
        list.add(new GlossaryTerm("Mens Rea","Latin for 'guilty mind.' The mental element required for criminal liability — the intent to commit a crime. Without mens rea, most criminal offences cannot be established.","Criminal"));
        list.add(new GlossaryTerm("Tort","A civil wrong that causes harm or loss, resulting in legal liability. Examples include negligence, defamation, and trespass. Governed by common law in India.","Civil"));
        list.add(new GlossaryTerm("Pleading","The formal written statements of each party's claims and defences in a civil lawsuit. Includes plaint, written statement, and replication.","Civil"));
        list.add(new GlossaryTerm("Arbitration","An alternative dispute resolution process where parties agree to resolve disputes outside the court. Governed by Arbitration and Conciliation Act 1996.","ADR"));
        list.add(new GlossaryTerm("Mediation","A voluntary, confidential process where a neutral third party (mediator) helps disputing parties reach a mutually acceptable resolution.","ADR"));
        list.add(new GlossaryTerm("Garnishee Order","A court order directing a third party (garnishee) who holds money belonging to a judgment debtor to pay that money to the judgment creditor.","Civil"));
        return list;
    }

    public static List<NotificationItem> getMockNotifications() {
        List<NotificationItem> list = new ArrayList<>();
        long now = System.currentTimeMillis();
        list.add(new NotificationItem("N001","⚖️","Case Update","Your case 'Property Dispute - Mumbai' has moved to Lawyer Review stage.",now - 3600000,false));
        list.add(new NotificationItem("N002","🤖","AI Analysis Complete","Contract Analyzer has finished reviewing your employment agreement. 3 risk areas found.",now - 7200000,false));
        list.add(new NotificationItem("N003","👨‍💼","Lawyer Matched","Adv. Priya Sharma has accepted your consultation request. Tap to view profile.",now - 86400000,true));
        list.add(new NotificationItem("N004","📅","Reminder","Your consultation with Adv. Rajesh Kumar is scheduled for tomorrow at 3:00 PM.",now - 172800000,true));
        list.add(new NotificationItem("N005","✅","Case Delivered","Your FIR guidance case has been completed. Download the report from My Cases.",now - 259200000,true));
        return list;
    }

    public static List<LegalCase> getMockCases() {
        List<LegalCase> list = new ArrayList<>();
        long now = System.currentTimeMillis();
        list.add(new LegalCase("C001","Property Dispute - Andheri","Property Law",
                "Neighbour encroached on boundary wall of residential property. Seeking injunction and removal of illegal construction.",
                LegalCase.STATUS_LAWYER_REVIEW,"Property Advisor","Adv. Mohammed Farooq",now - 86400000));
        list.add(new LegalCase("C002","Employment Termination","Labour Law",
                "Wrongfully terminated without notice or settlement. Salary for 3 months outstanding.",
                LegalCase.STATUS_AI_PROCESSING,"Labour Rights Advisor",null,now - 172800000));
        list.add(new LegalCase("C003","Consumer Complaint - Flipkart","Consumer Protection",
                "Product delivered was damaged. Company refusing refund despite multiple complaints within warranty period.",
                LegalCase.STATUS_APPROVED,"Consumer Rights Champion","Adv. Sunita Verma",now - 432000000));
        return list;
    }

    public static List<OnboardingPage> getOnboardingPages() {
        List<OnboardingPage> list = new ArrayList<>();
        list.add(new OnboardingPage("⚖️","AI-Powered Legal Guidance","Get instant answers to your legal questions powered by Gemini AI. Available 24/7, no appointment needed."));
        list.add(new OnboardingPage("👨‍💼","Connect with Expert Lawyers","Find and book verified advocates across 10+ practice areas anywhere in India."));
        list.add(new OnboardingPage("🤖","Specialized AI Agents","Use purpose-built AI agents for contracts, FIR help, property advice, startup law, and more."));
        list.add(new OnboardingPage("📋","Track Your Cases","Follow your legal matter from submission to resolution with real-time status updates."));
        return list;
    }
}
