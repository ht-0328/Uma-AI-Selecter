from playwright.sync_api import sync_playwright

def run(playwright):
    browser = playwright.chromium.launch()
    page = browser.new_page()
    try:
        page.goto("http://localhost:3000/prediction")

        # Wait for page load
        page.wait_for_selector("h1", timeout=10000)

        # Check H1 font size (should be 24px/1.5rem for text-2xl)
        h1 = page.locator("h1")
        font_size = h1.evaluate("el => window.getComputedStyle(el).fontSize")
        print(f"H1 Font Size: {font_size}")

        # Check grid layout
        grid = page.locator(".grid")
        display = grid.evaluate("el => window.getComputedStyle(el).display")
        print(f"Grid Display: {display}")

        # Take screenshot
        page.screenshot(path="verification/prediction_final.png")
        print("Screenshot taken")

    except Exception as e:
        print(f"Error: {e}")
        exit(1)
    finally:
        browser.close()

with sync_playwright() as playwright:
    run(playwright)
