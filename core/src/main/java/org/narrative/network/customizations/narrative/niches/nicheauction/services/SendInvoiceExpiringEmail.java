package org.narrative.network.customizations.narrative.niches.nicheauction.services;

import org.narrative.network.customizations.narrative.invoices.Invoice;
import org.narrative.network.customizations.narrative.niches.services.SendSingleNarrativeEmailTaskBase;

/**
 * Created by IntelliJ IDEA.
 * User: jonmark
 * Date: 3/2/18
 * Time: 10:40 AM
 */
public class SendInvoiceExpiringEmail extends SendSingleNarrativeEmailTaskBase {
    private Invoice invoice;

    public SendInvoiceExpiringEmail(Invoice invoice) {
        super(invoice.getUser());
        this.invoice = invoice;
    }

    @Override
    public boolean isAlwaysSendEmail() {
        return true;
    }

    public Invoice getInvoice() {
        return invoice;
    }
}
