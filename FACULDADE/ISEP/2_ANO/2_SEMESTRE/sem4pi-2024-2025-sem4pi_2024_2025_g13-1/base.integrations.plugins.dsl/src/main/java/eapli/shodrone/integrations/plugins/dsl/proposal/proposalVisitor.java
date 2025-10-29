// Generated from ./base.integrations.plugins.dsl/src/main/java/eapli/shodrone/integrations/plugins/dsl/proposal/proposal.g4 by ANTLR 4.13.2
package eapli.shodrone.integrations.plugins.dsl.proposal;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link proposalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface proposalVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link proposalParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(proposalParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#version1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersion1(proposalParser.Version1Context ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#version2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersion2(proposalParser.Version2Context ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#proposalVersion1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalVersion1(proposalParser.ProposalVersion1Context ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#proposalVersion2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalVersion2(proposalParser.ProposalVersion2Context ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(proposalParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#intro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntro(proposalParser.IntroContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#referencePart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferencePart(proposalParser.ReferencePartContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#companyNameClient}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompanyNameClient(proposalParser.CompanyNameClientContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#proposalBody1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalBody1(proposalParser.ProposalBody1Context ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#proposalBody2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalBody2(proposalParser.ProposalBody2Context ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#valuePart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValuePart(proposalParser.ValuePartContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#paragraph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParagraph(proposalParser.ParagraphContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#amount}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAmount(proposalParser.AmountContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#link}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLink(proposalParser.LinkContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#signature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature(proposalParser.SignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#extra}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtra(proposalParser.ExtraContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(proposalParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#attachmentTitle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttachmentTitle(proposalParser.AttachmentTitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#info}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfo(proposalParser.InfoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#proposalNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalNumber(proposalParser.ProposalNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#showLocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowLocation(proposalParser.ShowLocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#coordinateConj}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoordinateConj(proposalParser.CoordinateConjContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#coordinateGeo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoordinateGeo(proposalParser.CoordinateGeoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#showDate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowDate(proposalParser.ShowDateContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#showTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowTime(proposalParser.ShowTimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#showDuration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowDuration(proposalParser.ShowDurationContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#droneList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDroneList(proposalParser.DroneListContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#droneItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDroneItem(proposalParser.DroneItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(proposalParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#figureList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFigureList(proposalParser.FigureListContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#figureItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFigureItem(proposalParser.FigureItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#greetingVersion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreetingVersion(proposalParser.GreetingVersionContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#customerName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCustomerName(proposalParser.CustomerNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#companyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompanyName(proposalParser.CompanyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#streetAddress}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStreetAddress(proposalParser.StreetAddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#postalCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostalCode(proposalParser.PostalCodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#city}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCity(proposalParser.CityContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#country}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCountry(proposalParser.CountryContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#vatNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVatNumber(proposalParser.VatNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#referenceNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceNumber(proposalParser.ReferenceNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#referenceDate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceDate(proposalParser.ReferenceDateContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#signatureName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignatureName(proposalParser.SignatureNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#signatureTitle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignatureTitle(proposalParser.SignatureTitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate(proposalParser.DateContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime(proposalParser.TimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#hour}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHour(proposalParser.HourContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(proposalParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#floatVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatVal(proposalParser.FloatValContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#intVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntVal(proposalParser.IntValContext ctx);
	/**
	 * Visit a parse tree produced by {@link proposalParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(proposalParser.TextContext ctx);
}