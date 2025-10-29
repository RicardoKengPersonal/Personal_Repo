// Generated from ./base.integrations.plugins.dsl/src/main/java/eapli/shodrone/integrations/plugins/dsl/proposal/proposal.g4 by ANTLR 4.13.2
package eapli.shodrone.integrations.plugins.dsl.proposal;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link proposalParser}.
 */
public interface proposalListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link proposalParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(proposalParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(proposalParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#version1}.
	 * @param ctx the parse tree
	 */
	void enterVersion1(proposalParser.Version1Context ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#version1}.
	 * @param ctx the parse tree
	 */
	void exitVersion1(proposalParser.Version1Context ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#version2}.
	 * @param ctx the parse tree
	 */
	void enterVersion2(proposalParser.Version2Context ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#version2}.
	 * @param ctx the parse tree
	 */
	void exitVersion2(proposalParser.Version2Context ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#proposalVersion1}.
	 * @param ctx the parse tree
	 */
	void enterProposalVersion1(proposalParser.ProposalVersion1Context ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#proposalVersion1}.
	 * @param ctx the parse tree
	 */
	void exitProposalVersion1(proposalParser.ProposalVersion1Context ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#proposalVersion2}.
	 * @param ctx the parse tree
	 */
	void enterProposalVersion2(proposalParser.ProposalVersion2Context ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#proposalVersion2}.
	 * @param ctx the parse tree
	 */
	void exitProposalVersion2(proposalParser.ProposalVersion2Context ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(proposalParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(proposalParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#intro}.
	 * @param ctx the parse tree
	 */
	void enterIntro(proposalParser.IntroContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#intro}.
	 * @param ctx the parse tree
	 */
	void exitIntro(proposalParser.IntroContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#referencePart}.
	 * @param ctx the parse tree
	 */
	void enterReferencePart(proposalParser.ReferencePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#referencePart}.
	 * @param ctx the parse tree
	 */
	void exitReferencePart(proposalParser.ReferencePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#companyNameClient}.
	 * @param ctx the parse tree
	 */
	void enterCompanyNameClient(proposalParser.CompanyNameClientContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#companyNameClient}.
	 * @param ctx the parse tree
	 */
	void exitCompanyNameClient(proposalParser.CompanyNameClientContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#proposalBody1}.
	 * @param ctx the parse tree
	 */
	void enterProposalBody1(proposalParser.ProposalBody1Context ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#proposalBody1}.
	 * @param ctx the parse tree
	 */
	void exitProposalBody1(proposalParser.ProposalBody1Context ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#proposalBody2}.
	 * @param ctx the parse tree
	 */
	void enterProposalBody2(proposalParser.ProposalBody2Context ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#proposalBody2}.
	 * @param ctx the parse tree
	 */
	void exitProposalBody2(proposalParser.ProposalBody2Context ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#valuePart}.
	 * @param ctx the parse tree
	 */
	void enterValuePart(proposalParser.ValuePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#valuePart}.
	 * @param ctx the parse tree
	 */
	void exitValuePart(proposalParser.ValuePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#paragraph}.
	 * @param ctx the parse tree
	 */
	void enterParagraph(proposalParser.ParagraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#paragraph}.
	 * @param ctx the parse tree
	 */
	void exitParagraph(proposalParser.ParagraphContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#amount}.
	 * @param ctx the parse tree
	 */
	void enterAmount(proposalParser.AmountContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#amount}.
	 * @param ctx the parse tree
	 */
	void exitAmount(proposalParser.AmountContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#link}.
	 * @param ctx the parse tree
	 */
	void enterLink(proposalParser.LinkContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#link}.
	 * @param ctx the parse tree
	 */
	void exitLink(proposalParser.LinkContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#signature}.
	 * @param ctx the parse tree
	 */
	void enterSignature(proposalParser.SignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#signature}.
	 * @param ctx the parse tree
	 */
	void exitSignature(proposalParser.SignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#extra}.
	 * @param ctx the parse tree
	 */
	void enterExtra(proposalParser.ExtraContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#extra}.
	 * @param ctx the parse tree
	 */
	void exitExtra(proposalParser.ExtraContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(proposalParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(proposalParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#attachmentTitle}.
	 * @param ctx the parse tree
	 */
	void enterAttachmentTitle(proposalParser.AttachmentTitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#attachmentTitle}.
	 * @param ctx the parse tree
	 */
	void exitAttachmentTitle(proposalParser.AttachmentTitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#info}.
	 * @param ctx the parse tree
	 */
	void enterInfo(proposalParser.InfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#info}.
	 * @param ctx the parse tree
	 */
	void exitInfo(proposalParser.InfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#proposalNumber}.
	 * @param ctx the parse tree
	 */
	void enterProposalNumber(proposalParser.ProposalNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#proposalNumber}.
	 * @param ctx the parse tree
	 */
	void exitProposalNumber(proposalParser.ProposalNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#showLocation}.
	 * @param ctx the parse tree
	 */
	void enterShowLocation(proposalParser.ShowLocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#showLocation}.
	 * @param ctx the parse tree
	 */
	void exitShowLocation(proposalParser.ShowLocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#coordinateConj}.
	 * @param ctx the parse tree
	 */
	void enterCoordinateConj(proposalParser.CoordinateConjContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#coordinateConj}.
	 * @param ctx the parse tree
	 */
	void exitCoordinateConj(proposalParser.CoordinateConjContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#coordinateGeo}.
	 * @param ctx the parse tree
	 */
	void enterCoordinateGeo(proposalParser.CoordinateGeoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#coordinateGeo}.
	 * @param ctx the parse tree
	 */
	void exitCoordinateGeo(proposalParser.CoordinateGeoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#showDate}.
	 * @param ctx the parse tree
	 */
	void enterShowDate(proposalParser.ShowDateContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#showDate}.
	 * @param ctx the parse tree
	 */
	void exitShowDate(proposalParser.ShowDateContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#showTime}.
	 * @param ctx the parse tree
	 */
	void enterShowTime(proposalParser.ShowTimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#showTime}.
	 * @param ctx the parse tree
	 */
	void exitShowTime(proposalParser.ShowTimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#showDuration}.
	 * @param ctx the parse tree
	 */
	void enterShowDuration(proposalParser.ShowDurationContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#showDuration}.
	 * @param ctx the parse tree
	 */
	void exitShowDuration(proposalParser.ShowDurationContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#droneList}.
	 * @param ctx the parse tree
	 */
	void enterDroneList(proposalParser.DroneListContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#droneList}.
	 * @param ctx the parse tree
	 */
	void exitDroneList(proposalParser.DroneListContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#droneItem}.
	 * @param ctx the parse tree
	 */
	void enterDroneItem(proposalParser.DroneItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#droneItem}.
	 * @param ctx the parse tree
	 */
	void exitDroneItem(proposalParser.DroneItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(proposalParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(proposalParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#figureList}.
	 * @param ctx the parse tree
	 */
	void enterFigureList(proposalParser.FigureListContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#figureList}.
	 * @param ctx the parse tree
	 */
	void exitFigureList(proposalParser.FigureListContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#figureItem}.
	 * @param ctx the parse tree
	 */
	void enterFigureItem(proposalParser.FigureItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#figureItem}.
	 * @param ctx the parse tree
	 */
	void exitFigureItem(proposalParser.FigureItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#greetingVersion}.
	 * @param ctx the parse tree
	 */
	void enterGreetingVersion(proposalParser.GreetingVersionContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#greetingVersion}.
	 * @param ctx the parse tree
	 */
	void exitGreetingVersion(proposalParser.GreetingVersionContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#customerName}.
	 * @param ctx the parse tree
	 */
	void enterCustomerName(proposalParser.CustomerNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#customerName}.
	 * @param ctx the parse tree
	 */
	void exitCustomerName(proposalParser.CustomerNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#companyName}.
	 * @param ctx the parse tree
	 */
	void enterCompanyName(proposalParser.CompanyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#companyName}.
	 * @param ctx the parse tree
	 */
	void exitCompanyName(proposalParser.CompanyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#streetAddress}.
	 * @param ctx the parse tree
	 */
	void enterStreetAddress(proposalParser.StreetAddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#streetAddress}.
	 * @param ctx the parse tree
	 */
	void exitStreetAddress(proposalParser.StreetAddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#postalCode}.
	 * @param ctx the parse tree
	 */
	void enterPostalCode(proposalParser.PostalCodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#postalCode}.
	 * @param ctx the parse tree
	 */
	void exitPostalCode(proposalParser.PostalCodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#city}.
	 * @param ctx the parse tree
	 */
	void enterCity(proposalParser.CityContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#city}.
	 * @param ctx the parse tree
	 */
	void exitCity(proposalParser.CityContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#country}.
	 * @param ctx the parse tree
	 */
	void enterCountry(proposalParser.CountryContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#country}.
	 * @param ctx the parse tree
	 */
	void exitCountry(proposalParser.CountryContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#vatNumber}.
	 * @param ctx the parse tree
	 */
	void enterVatNumber(proposalParser.VatNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#vatNumber}.
	 * @param ctx the parse tree
	 */
	void exitVatNumber(proposalParser.VatNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#referenceNumber}.
	 * @param ctx the parse tree
	 */
	void enterReferenceNumber(proposalParser.ReferenceNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#referenceNumber}.
	 * @param ctx the parse tree
	 */
	void exitReferenceNumber(proposalParser.ReferenceNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#referenceDate}.
	 * @param ctx the parse tree
	 */
	void enterReferenceDate(proposalParser.ReferenceDateContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#referenceDate}.
	 * @param ctx the parse tree
	 */
	void exitReferenceDate(proposalParser.ReferenceDateContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#signatureName}.
	 * @param ctx the parse tree
	 */
	void enterSignatureName(proposalParser.SignatureNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#signatureName}.
	 * @param ctx the parse tree
	 */
	void exitSignatureName(proposalParser.SignatureNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#signatureTitle}.
	 * @param ctx the parse tree
	 */
	void enterSignatureTitle(proposalParser.SignatureTitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#signatureTitle}.
	 * @param ctx the parse tree
	 */
	void exitSignatureTitle(proposalParser.SignatureTitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#date}.
	 * @param ctx the parse tree
	 */
	void enterDate(proposalParser.DateContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#date}.
	 * @param ctx the parse tree
	 */
	void exitDate(proposalParser.DateContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#time}.
	 * @param ctx the parse tree
	 */
	void enterTime(proposalParser.TimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#time}.
	 * @param ctx the parse tree
	 */
	void exitTime(proposalParser.TimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#hour}.
	 * @param ctx the parse tree
	 */
	void enterHour(proposalParser.HourContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#hour}.
	 * @param ctx the parse tree
	 */
	void exitHour(proposalParser.HourContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(proposalParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(proposalParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#floatVal}.
	 * @param ctx the parse tree
	 */
	void enterFloatVal(proposalParser.FloatValContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#floatVal}.
	 * @param ctx the parse tree
	 */
	void exitFloatVal(proposalParser.FloatValContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#intVal}.
	 * @param ctx the parse tree
	 */
	void enterIntVal(proposalParser.IntValContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#intVal}.
	 * @param ctx the parse tree
	 */
	void exitIntVal(proposalParser.IntValContext ctx);
	/**
	 * Enter a parse tree produced by {@link proposalParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(proposalParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link proposalParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(proposalParser.TextContext ctx);
}